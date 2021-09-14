package com.uahage.api.controller;

import com.amazonaws.util.IOUtils;
import com.uahage.api.dto.*;
import com.uahage.api.mapper.UserMapper;
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
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void joinTest() throws Exception {
//        List<Character> babyGenders = new ArrayList<>();
//        babyGenders.add('M');
//        babyGenders.add('F');
//        List<String> babyBirthDays = new ArrayList<>();
//        babyBirthDays.add("2025-05-15");
//        babyBirthDays.add("2025-06-21");
//
//        UserJoinRequest joinRequest = new UserJoinRequest(null,"nickname", (short)6, babyGenders, babyBirthDays);
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        final String token = "v48e_SI7dLHCFDH-PuzpUW2vbOoNNRTpXQLLgQopyNoAAAF7yFMA1A";
//        request.addHeader("Authorization", "bearer ".concat(token));
//
//        ResponseEntity responseEntity = userController.joinWithKakao(request, joinRequest);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
//
//    @Test
//    public void editTest() throws Exception {
//        File file = new File("C:/images/test.jpg");
//        FileInputStream input = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
//        List<MultipartFile> files = new ArrayList<>();
//        files.add(multipartFile);
//
//        List<Character> babyGenders = new ArrayList<>();
//        babyGenders.add('M');
//        babyGenders.add('F');
//        List<String> babyBirthDays = new ArrayList<>();
//        babyBirthDays.add("2025-05-15");
//        babyBirthDays.add("2025-06-21");
//
//        UserEditRequest userEditRequest = new UserEditRequest(11L,files,'Y',"nickname",(short)3, babyGenders, babyBirthDays);
//        ResponseEntity responseEntity = userController.edit(userEditRequest);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
//
//    @Test
//    public void destroyTest() throws Exception {
//        UserDestroyRequest userDestroyRequest = new UserDestroyRequest(11L);
//        ResponseEntity responseEntity = userController.destroy(userDestroyRequest);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
//
//    @Test
//    public void verifyDuplicateNicknameTest(){
//        UserVerifyDuplicateNicknameRequest userVerifyDuplicateRequest = new UserVerifyDuplicateNicknameRequest("email");
//        ResponseEntity responseEntity =userController.verifyDuplicateNickname(userVerifyDuplicateRequest);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
//
//    @Test
//    public void verifyDuplicateEmailTest(){
//        UserVerifyDuplicateEmailRequest userVerifyDuplicateRequest = new UserVerifyDuplicateEmailRequest("KAKAO:NAVER");
//        ResponseEntity responseEntity =userController.verifyDuplicateEmail(userVerifyDuplicateRequest);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
//
//    @Test
//    public void showTest(){
//        ResponseEntity responseEntity = userController.show(12L);
//        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
//        System.out.println(result.get("data"));
//        assertTrue(result.get("statusCode").toString().equals("200"));
//    }
}