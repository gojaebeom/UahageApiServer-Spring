package kr.co.hohocompany.service;

import kr.co.hohocompany.domain.user.User;
import kr.co.hohocompany.domain.user.UserDetail;
import kr.co.hohocompany.domain.user.UserImage;
import kr.co.hohocompany.dto.ReqEditUserDto;
import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import kr.co.hohocompany.repository.UserDetailRepository;
import kr.co.hohocompany.repository.UserImageRepository;
import kr.co.hohocompany.repository.UserRepository;
import kr.co.hohocompany.repository.mapper.UserImageForUserEdit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;
    private UserImageRepository userImageRepository;

    private TokenService tokenService;
    private S3Service s3Service;

    public ResJoinDto joinWithOauth(ReqJoinDto reqJoinDto) {

        User user = userRepository.findByEmail(reqJoinDto.getEmail());
        System.out.println("유저: ".concat(String.valueOf(user)));
        System.out.println(user);
        if(user == null){
            // 회원 가입
            Long duplicatedCount = userRepository.countByNickname(reqJoinDto.getNickname());
            if(duplicatedCount > 0){
                throw new IllegalArgumentException("닉네임이 중복되었습니다.");
            }

            user = reqJoinDto.toUser();
            user = userRepository.save(user);

            UserDetail userDetail = reqJoinDto.toUserDetail(user);
            userDetailRepository.save(userDetail);
        }

        String act = tokenService.createAct(user.getId());

        return ResJoinDto.builder()
                .accessToken(act)
                .refreshToken(act)
                .build();
    }

    public void edit(ReqEditUserDto reqEditUserDto) throws Exception {
        System.out.println("이상무");
        Optional<User> _user = userRepository.findById(reqEditUserDto.getUserId());

        System.out.println(_user);
        if(_user.isEmpty()) throw new Exception("유저 ID 미확인");
        User user = _user.get();
        System.out.println(user);

        List<String> fileNames = new ArrayList<>();

        if(reqEditUserDto.getImageInit() != null && reqEditUserDto.getImageInit() == 'Y'){
            System.out.println("초기화/이미지 삭제");
            List<UserImageForUserEdit> userImages = userImageRepository.findAllByUserId(reqEditUserDto.getUserId());
            System.out.println(userImages.get(0).getImagePath());
            for(UserImageForUserEdit userImage : userImages){
                String[] imagePaths = userImage.getImagePath().split("/");
                s3Service.delete("profiles/".concat(imagePaths[imagePaths.length-1]));
                userImageRepository.deleteById(userImage.getId());
            }
        }else{
            System.out.println("업로드");
            List<UserImage> userImages = new ArrayList<>();

            System.out.println(reqEditUserDto.getImages());
            if(reqEditUserDto.getImages() != null){
                System.out.println("있음");
                fileNames = s3Service.upload("profiles", reqEditUserDto.getUserId(), reqEditUserDto.getImages());
                for(String fileName : fileNames){
                    UserImage userImage = UserImage.builder()
                            .user(user)
                            .imagePath(fileName)
                            .build();
                    userImages.add(userImage);
                }
                userImageRepository.saveAll(userImages);
            }
        }

        System.out.println(reqEditUserDto);

        if(reqEditUserDto.getNickname() != null){
            user.setNickname(reqEditUserDto.getNickname());
        }
        UserDetail userDetail = userDetailRepository.findByUserId(reqEditUserDto.getUserId());
        if(reqEditUserDto.getAgeGroupType() != null){
            userDetail.setAgeGroupType(reqEditUserDto.getAgeGroupType());
        }
        if(reqEditUserDto.getBabyBirthday() != null){
            userDetail.setBabyBirthday(reqEditUserDto.getBabyBirthday());
        }
        if(reqEditUserDto.getNickname() != null){
            userDetail.setBabyGender(reqEditUserDto.getBabyGender());
        }
    }
}
