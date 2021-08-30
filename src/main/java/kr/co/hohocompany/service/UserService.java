package kr.co.hohocompany.service;

import kr.co.hohocompany.domain.user.User;
import kr.co.hohocompany.domain.user.UserDetail;
import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import kr.co.hohocompany.repository.UserDetailRepository;
import kr.co.hohocompany.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;
    private TokenService tokenService;

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
}
