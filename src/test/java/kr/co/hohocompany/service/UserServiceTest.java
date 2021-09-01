package kr.co.hohocompany.service;

import com.amazonaws.util.IOUtils;
import kr.co.hohocompany.dto.ReqEditUserDto;
import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import kr.co.hohocompany.dto.ResShowUserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void joinWithOauth2Test(){
        ReqJoinDto joinDto = ReqJoinDto.builder()
                .email("KAKAO.test2@gmail.com")
                .nickname("테스트아이디3")
                .ageGroupType((short)2)
                .babyGender('M')
                .babyBirthday("2020년 8월 13일")
                .build();

        ResJoinDto resJoinDto = userService.joinWithOauth(joinDto);
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
                .id(150l)
                .images(files)
                .imageInit('N')
                .ageGroupType((short) 3)
                .babyBirthday("2020-06-21")
                .babyGender('A')
                .nickname("안녕봇")
                .build();

        userService.edit(reqEditUserDto);
    }

    @Test
    public void destroyTest(){
        userService.destroy(151l);
    }

    @Test
    public void verifyDuplicateNicknameTest(){
        final String NICKNAME = "미횬";
        Boolean result = userService.verifyDuplicateNickname(NICKNAME);
        if(result) log.info("사용가능한 닉네임입니다.");
        else log.info("중복된 닉네임입니다.");
    }

    @Test
    public void verifyDuplicateEmailTest(){
        final String EMAIL = "";
        Boolean result = userService.verifyDuplicateEmail(EMAIL);
        if(result) log.info("사용가능한 이메일입니다.");
        else log.info("중복된 이메일입니다.");
    }

    @Test
    public void showTest(){
        final Long USER_ID = 103L;
        ResShowUserDto resUserShowDto = userService.show(USER_ID);
        System.out.println(resUserShowDto);
    }
}