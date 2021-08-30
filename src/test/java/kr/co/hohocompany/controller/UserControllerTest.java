package kr.co.hohocompany.controller;

import kr.co.hohocompany.dto.ReqEditUserDto;
import kr.co.hohocompany.dto.ReqJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void JoinWithKakaoTest() throws Exception {
        ReqJoinDto joinDto = ReqJoinDto.builder()
                .nickname("테스트아이디3")
                .ageGroupType((short)2)
                .babyGender("M")
                .babyBirthday("2020년 8월 13일")
                .build();

        MockHttpServletRequest request = new MockHttpServletRequest();
        final String token = "oe3jCjbQwzuuXXLzETrTOuCIVMOMfpdIDHpEGQopcJ8AAAF7lOWvRA";
        request.addHeader("Authorization", "bearer ".concat(token));

        ResponseEntity responseEntity = userController.joinWithKakao(request, joinDto);
        System.out.println(responseEntity);
    }

    @Test
    public void editTest() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("test.jpg", new FileInputStream(new File("C:/images/test.jpg")));
        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        ReqEditUserDto reqEditUserDto = ReqEditUserDto.builder()
                .images(files)
                .build();

        userController.edit(reqEditUserDto);
    }
}