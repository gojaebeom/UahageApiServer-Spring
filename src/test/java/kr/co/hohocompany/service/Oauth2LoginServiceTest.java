package kr.co.hohocompany.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Oauth2LoginServiceTest {

    @Autowired
    private Oauth2LoginService oauth2LoginService;

    @Test
    public void verifyWithKakaoTokenThenGetEmailTest() throws Exception {
        final String token = "oe3jCjbQwzuuXXLzETrTOuCIVMOMfpdIDHpEGQopcJ8AAAF7lOWvRA";
        oauth2LoginService.verifyWithKakaoTokenThenGetEmail(token);
    }
}