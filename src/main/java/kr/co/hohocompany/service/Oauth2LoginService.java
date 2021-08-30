package kr.co.hohocompany.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class Oauth2LoginService {

    public String verifyWithKakaoTokenThenGetEmail(String accessToken) throws Exception {
        accessToken = splitTokenStringThenGetToken(accessToken);

        log.info(accessToken);

        final String KAKAO_VERIFY_URL = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> resultMap;
        try{
            resultMap = restTemplate.exchange(KAKAO_VERIFY_URL, HttpMethod.GET, entity, Map.class);
        }catch (HttpClientErrorException e){
            throw new Exception("카카오 로그인에 실패하였습니다.");
        }
        System.out.println(resultMap);
        String kakaoUserId = "KAKAO."+resultMap.getBody().get("id").toString();
        return kakaoUserId;
    }

    public String verifyWithNaverTokenThenGetEmail(String accessToken) throws Exception {
        accessToken = splitTokenStringThenGetToken(accessToken);

        log.info(accessToken);

        final String KAKAO_VERIFY_URL = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> resultMap;
        try{
            resultMap = restTemplate.exchange(KAKAO_VERIFY_URL, HttpMethod.GET, entity, Map.class);
        }catch (HttpClientErrorException e){
            throw new Exception("카카오 로그인에 실패하였습니다.");
        }
        System.out.println(resultMap);
        String kakaoUserId = "KAKAO."+resultMap.getBody().get("id").toString();
        return kakaoUserId;
    }

    private String splitTokenStringThenGetToken(String accessToken) throws Exception {
        try{
            accessToken = accessToken.split("bearer ")[1];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new Exception("엑세스 토큰 값이 올바르지 않습니다.");
        }
        return accessToken;
    }

}
