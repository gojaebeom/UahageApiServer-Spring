package com.uahage.api.service;

import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserBaby;
import com.uahage.api.domain.user.UserImage;
import com.uahage.api.dto.*;
import com.uahage.api.mapper.UserMapper;
import com.uahage.api.repository.UserBabyRepository;
import com.uahage.api.repository.UserImageRepository;
import com.uahage.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private UserBabyRepository userBabyRepository;
    private UserImageRepository userImageRepository;

    private TokenService tokenService;
    private S3Service s3Service;

    public UserJoinResponse join(UserJoinRequest joinRequest) {
        User user = userRepository.findByEmail(joinRequest.getEmailOrThrowException());
        log.info("기존 회원 유무 확인");

        if(user == null){
            // 회원 가입
            log.info("회원가입 진행");

            Short duplicatedCount = userRepository.countByNickname(joinRequest.getNicknameOrThrowException());
            log.info("중복채크 값, 0 이상일 시 중복:",duplicatedCount.toString());
            if(duplicatedCount > 0){
                throw new IllegalArgumentException("닉네임이 중복되었습니다.");
            }

            user = joinRequest.toUser();
            userRepository.save(user);

            List<UserBaby> userBabies = joinRequest.toUserBabies(user);
            if(userBabies != null){
                log.info("유저 아기정보 저장");
                userBabyRepository.saveAll(userBabies);
            }
        }

        log.info("로그인 진행");
        String act = tokenService.createAct(user.getId());
        String rft = tokenService.createRft(user.getId());

        log.info("엑세스 토큰 발급 완료");
        System.out.println(act);

        return UserJoinResponse.builder()
                .accessToken(act)
                .refreshToken(rft)
                .build();
    }

    public void edit(UserEditRequest userEditRequest) throws Exception {
        userEditRequest.verifyId();
        User user = userRepository.findUserById(userEditRequest.getId());
        if(user == null) throw new IllegalArgumentException("일치하는 유저가 없습니다");

        if(userEditRequest.verifyImageInit()){
            destroyProfile(userEditRequest.getId());
        }

        if(userEditRequest.verifyImages()){
            destroyProfile(userEditRequest.getId());

            log.info("[ 이미지 업로드 요청 -> s3 서버에 이미지 업로드, DB에 이미지 이름 등록 ]");
            List<UserImage> userImages = new ArrayList<>();
            List<HashMap<String, String>> fileNames = s3Service.uploads("profiles", userEditRequest.getId(), userEditRequest.getImages(), true);

            for(HashMap<String, String> fileName : fileNames){
                UserImage userImage = UserImage.builder()
                        .user(user)
                        .imagePath(fileName.get("origin"))
                        .previewImagePath(fileName.get("preview"))
                        .build();
                userImages.add(userImage);
            }
            System.out.println(userImages);
            userImageRepository.saveAll(userImages);
        }

        if(userEditRequest.verifyNickname()){
            log.info("[ 회원 닉네임 정보 수정 : SOON ]");
            user.setNickname(userEditRequest.getNickname());
            log.info("[ 회원 닉네임 정보 수정 : OK ]");
        }
        if(userEditRequest.verifyAgeGroupType()){
            log.info("[ 회원 연령층 정보 수정 : SOON ]");
            user.setAgeGroupType(userEditRequest.getAgeGroupType());
            log.info("[ 회원 연령층 정보 수정 : SOON ]");
        }

        if(userEditRequest.verifyBabiesInfo()){
            log.info("[ 기존의 회원 아기정보 삭제 : SOON ]");
            List<UserBaby> userBabies = userBabyRepository.findAllByUser(user);
            System.out.println(userBabies);
            userBabyRepository.deleteAll(userBabies);
            log.info("[ 기존의 회원 아기정보 삭제 : OK ]");

            log.info("[ 새로운 회원 아기정보 생성  : SOON ]");
            userBabies = userEditRequest.toUserBabies(user);
            System.out.println(userBabies);
            userBabyRepository.saveAll(userBabies);
            log.info("[ 새로운 회원 아기정보 생성  : OK ]");
        }
    }

    private void destroyProfile(Long id) throws Exception {
        log.info("[ 이미지 초기화 요청 -> s3 서버에서 이미지 삭제, DB 이미지 레코드 삭제 ]");
        List<UserImage> userImages = userImageRepository.findAllByUserId(id);
        for(UserImage userImage : userImages){
            String[] imagePaths = userImage.getImagePath().split("/");
            String[] previewImagePaths = userImage.getPreviewImagePath().split("/");
            s3Service.delete("profiles/".concat(imagePaths[imagePaths.length-1]));
            s3Service.delete("profiles/".concat(previewImagePaths[previewImagePaths.length-1]));
            userImageRepository.deleteById(userImage.getId());
        }
    }

    public void destroy(Long id) throws Exception {
        destroyProfile(id);
        log.info("[ 회원 탈퇴 요청 -> 탈퇴 처리 ]");
        User user = userRepository.findUserById(id);
        if(user == null) throw new IllegalArgumentException("일치하는 유저가 없습니다.");
        userRepository.delete(user);
    }

    public Boolean verifyDuplicateNickname(UserVerifyDuplicateNicknameRequest userVerifyDuplicateNicknameRequest) {
        userVerifyDuplicateNicknameRequest.verifyNickname();
        log.info("[ 닉네임 중복 채크 ]");
        if(userRepository.countByNickname(userVerifyDuplicateNicknameRequest.getNickname()) != 0)
            return false;

        return true;
    }

    public Boolean verifyDuplicateEmail(UserVerifyDuplicateEmailRequest userVerifyDuplicateEmailRequest) {
        userVerifyDuplicateEmailRequest.verifyEmail();
        log.info("[ 이메일 중복 채크 ]");
        if(userRepository.countByEmail(userVerifyDuplicateEmailRequest.getEmail()) != 0)
            return false;

        return true;
    }

    public UserShowResponse show(Long id) {
        return userMapper.findOneById(id);
    }
}
