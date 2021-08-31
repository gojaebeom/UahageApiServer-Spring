package kr.co.hohocompany.controller;

import io.jsonwebtoken.ExpiredJwtException;
import kr.co.hohocompany.dto.ReqEditUserDto;
import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import kr.co.hohocompany.service.Oauth2LoginService;
import kr.co.hohocompany.service.S3Service;
import kr.co.hohocompany.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private Oauth2LoginService oauth2LoginService;

    @PostMapping("/kakao-login")
    public ResponseEntity<?> joinWithKakao(HttpServletRequest request, ReqJoinDto reqJoinDto) throws Exception {
        String accessTokenString = request.getHeader("Authorization");
        String email;

        Map<String, Object> response = new HashMap<>();
        try{
            email = oauth2LoginService.verifyWithKakaoTokenThenGetEmail(accessTokenString);
            reqJoinDto.setEmail(email);
            ResJoinDto resJoinDto = userService.joinWithOauth(reqJoinDto);

            response.put("message","로그인이 정상적으로 처리되었습니다.");
            response.put("statusCode", 200);
            response.put("data", resJoinDto);
            return ResponseEntity.ok(response);
        }catch(Exception e){

            response.put("message", e.getMessage());
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(ReqEditUserDto reqEditUserDto) throws Exception {
        System.out.println(reqEditUserDto);
        Map<String, Object> response = new HashMap<>();
        try{
            userService.edit(reqEditUserDto);

            response.put("message","회원 수정이 정상적으로 처리되었습니다.");
            response.put("statusCode", 200);
            response.put("data", true);
            return ResponseEntity.ok(response);
        }catch(Exception e){

            response.put("message", e.getMessage());
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
