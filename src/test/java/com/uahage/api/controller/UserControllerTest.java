package com.uahage.api.controller;

import com.uahage.api.dto.ReqJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void JoinWithKakaoTest() throws Exception {
        ReqJoinDto joinDto = ReqJoinDto.builder()
                .nickname("테스트아이디3")
                .ageGroupType((short)2)
                .babyGender('M')
                .babyBirthday("2020년 8월 13일")
                .build();

        MockHttpServletRequest request = new MockHttpServletRequest();
        final String token = "oe3jCjbQwzuuXXLzETrTOuCIVMOMfpdIDHpEGQopcJ8AAAF7lOWvRA";
        request.addHeader("Authorization", "bearer ".concat(token));

        ResponseEntity responseEntity = userController.joinWithKakao(request, joinDto);
        System.out.println(responseEntity);
    }

    @Test
    public void destroyTest() throws Exception {
        ResponseEntity responseEntity = userController.destroy(148l);
        System.out.println(responseEntity);
    }

    @Test
    public void verifyDuplicateNickname() {
        final String NICKNAME = "미횬";
        ResponseEntity responseEntity = userController.verifyDuplicateNickname(NICKNAME);
        System.out.println(responseEntity);
    }

    @Test
    public void verifyDuplicateEmail(){
        final String EMAIL = "";
        ResponseEntity responseEntity = userController.verifyDuplicateEmail(EMAIL);
        System.out.println(responseEntity);
    }

    @Test
    public void showTest(){
        final Long USER_ID = 145L;
        ResponseEntity responseEntity = userController.show(USER_ID);
        System.out.println(responseEntity);
    }
}