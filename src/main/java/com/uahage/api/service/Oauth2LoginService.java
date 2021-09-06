package com.uahage.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Oauth2LoginService {

    public String verifyWithKakaoTokenThenGetEmail(String accessToken) throws Exception {
        accessToken = splitTokenStringThenGetToken(accessToken);

        final String KAKAO_VERIFY_URL = "https://kapi.kakao.com/v2/user/me";

        ResponseEntity<Map> resultMap =  requestOauthServer(accessToken, KAKAO_VERIFY_URL, "카카오");

        HashMap<String, Object> kakaoAccount = (HashMap<String, Object>) resultMap.getBody().get("kakao_account");
        String kakaoEmail = "KAKAO:"+kakaoAccount.get("email");
        log.info("카카오 이메일 받기 완료.");
        return kakaoEmail;
    }

    public String verifyWithNaverTokenThenGetEmail(String accessToken) throws Exception {
        accessToken = splitTokenStringThenGetToken(accessToken);

        final String NAVER_VERIFY_URL = "https://openapi.naver.com/v1/nid/me";

        ResponseEntity<Map> resultMap =  requestOauthServer(accessToken, NAVER_VERIFY_URL, "네이버");

        HashMap<String, Object> naverResponse = (HashMap<String, Object>) resultMap.getBody().get("response");
        String naverEmail = "NAVER:"+naverResponse.get("email");
        log.info("네이버 이메일 받기 완료.");
        return naverEmail;
    }

    private ResponseEntity<Map> requestOauthServer(String accessToken, String URL, String TYPE) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> resultMap;
        try{
            resultMap = restTemplate.exchange(URL, HttpMethod.GET, entity, Map.class);
            System.out.println(resultMap);

        }catch (HttpClientErrorException e){
            throw new Exception(TYPE+" 로그인에 실패하였습니다.");
        }
        return resultMap;
    }

    private String splitTokenStringThenGetToken(String accessToken) throws Exception {
        try{
            accessToken = accessToken.split("bearer ")[1];
            log.info("정상적인 토큰 받음.");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new Exception("엑세스 토큰 값이 올바르지 않습니다.");
        }
        return accessToken;
    }

}
