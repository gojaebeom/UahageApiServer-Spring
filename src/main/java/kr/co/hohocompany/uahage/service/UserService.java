package kr.co.hohocompany.uahage.service;

import kr.co.hohocompany.uahage.domain.user.User;
import kr.co.hohocompany.uahage.domain.user.UserDetail;
import kr.co.hohocompany.uahage.dto.UserCreateRequest;
import kr.co.hohocompany.uahage.dto.UserUpdateRequest;
import kr.co.hohocompany.uahage.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.BindException;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserCreateRequest userCreateRequest) throws Exception {

        User user = userCreateRequest.toUserEntity();
        UserDetail userDetail = userCreateRequest.toUserDetailEntity(user);

        userRepository.create(user, userDetail, null);
    }

    public void update(UserUpdateRequest userUpdateRequest) throws Exception {

        User user = userUpdateRequest.toUserEntity();
        UserDetail userDetail = userUpdateRequest.toUserDetailEntity(user);

        userRepository.update(user, userDetail, null);
    }

    // TODO: 닉네임 유효성 검사
    public Boolean validateNickname(String nickname) throws Exception {

        String[] words = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅢ", "ㅔ", "ㅖ"};
        for(String word : words ) {
            if(nickname.contains(word)){
                throw new BindException("올바르지 않은 형식입니다.");
            }
        }

        String nicknameExp = "^[a-zA-Zㄱ-힣0-9]*$";

        if(!nickname.matches(nicknameExp)){
            throw new BindException("닉네임에 공백 또는 특수문자를 사용할 수 없습니다.");
        }

        if(nickname.length() < 2 || nickname.length() > 12) {
            throw new BindException("닉네임은 2-12 자리 사이로 입력해주세요.");
        }



        return userRepository.validateNickname(nickname);
    }
}
