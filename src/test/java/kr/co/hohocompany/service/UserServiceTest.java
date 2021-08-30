package kr.co.hohocompany.service;

import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void joinWithOauth2Test(){
        ReqJoinDto joinDto = ReqJoinDto.builder()
                .email("KAKAO.test@gmail.com")
                .nickname("테스트아이디2")
                .ageGroupType((short)2)
                .babyGender("M")
                .babyBirthday("2020년 8월 13일")
                .build();

        ResJoinDto resJoinDto = userService.joinWithOauth(joinDto);
        System.out.println(resJoinDto);
    }
}