package com.uahage.api.service;

import com.amazonaws.util.IOUtils;
import com.uahage.api.dto.ReqEditUserDto;
import com.uahage.api.dto.ReqJoinDto;
import com.uahage.api.dto.ResJoinDto;
import com.uahage.api.dto.ResShowUserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void joinWithOauthTest(){
        ReqJoinDto joinDto = ReqJoinDto.builder()
                .email("KAKAO.test450@gmail.com")
                .nickname("테스트아이디671")
                .ageGroupType((short)2)
                .babyGender('M')
                .babyBirthday("2020년 8월 13일")
                .build();

        ResJoinDto resJoinDto = userService.join(joinDto);
        System.out.println(resJoinDto);
    }

    @Test
    public void editTest() throws Exception {
        File file = new File("C:/images/test.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);

        ReqEditUserDto reqEditUserDto = ReqEditUserDto.builder()
                .id(132L)
                .images(files)
                .imageInit('Y')
                .ageGroupType((short) 3)
                .babyBirthday("2020-06-21")
                .babyGender('A')
                .nickname("안녕봇")
                .build();

        userService.edit(reqEditUserDto);
    }

    @Test
    public void destroyTest(){
        userService.destroy(171l);
    }

    @Test
    public void verifyDuplicateNicknameTest(){
        final String NICKNAME = "완득이아빠";
        Boolean result = userService.verifyDuplicateNickname(NICKNAME);
        if(result) log.info("사용가능한 닉네임입니다.");
        else log.info("중복된 닉네임입니다.");
    }

    @Test
    public void verifyDuplicateEmailTest(){
        final String EMAIL = "KAKAO.const.gjb@gmail";
        Boolean result = userService.verifyDuplicateEmail(EMAIL);
        if(result) log.info("사용가능한 이메일입니다.");
        else log.info("중복된 이메일입니다.");
    }

    @Test
    public void showTest(){
        final Long USER_ID = 170L;
        ResShowUserDto resUserShowDto = userService.show(USER_ID);
        System.out.println(resUserShowDto);
    }
}