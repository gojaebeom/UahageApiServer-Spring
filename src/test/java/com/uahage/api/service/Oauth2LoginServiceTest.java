package com.uahage.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Oauth2LoginServiceTest {

    @Autowired
    private Oauth2LoginService oauth2LoginService;

    @Test
    public void oAuthGetInfoTest() throws Exception {
        String kakaoEmail = oauth2LoginService.verifyWithKakaoTokenThenGetEmail("bearer skRgFdriRZcAOGwWaGa9JJV4b7ZVTZ9HqIkqNworDNMAAAF7uZfP0Q");
        System.out.println(kakaoEmail);
    }
}