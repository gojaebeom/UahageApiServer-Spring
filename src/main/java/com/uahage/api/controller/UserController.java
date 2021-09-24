package com.uahage.api.controller;

import com.uahage.api.dto.*;
import com.uahage.api.service.Oauth2LoginService;
import com.uahage.api.service.TokenService;
import com.uahage.api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private Oauth2LoginService oauth2LoginService;

    @PostMapping("/kakao-login")
    public ResponseEntity<?> joinWithKakao(HttpServletRequest request, UserJoinRequest joinRequest) throws Exception {
        String accessTokenString = request.getHeader("Authorization");
        String email = oauth2LoginService.verifyWithKakaoTokenThenGetEmail(accessTokenString);
        return join(joinRequest, email);
    }

    @PostMapping("/naver-login")
    public ResponseEntity<?> joinWithNaver(HttpServletRequest request, UserJoinRequest joinRequest) throws Exception {
        log.info("네이버 로그인 요청");
        String accessTokenString = request.getHeader("Authorization");
        String email = oauth2LoginService.verifyWithNaverTokenThenGetEmail(accessTokenString);
        return join(joinRequest, email);
    }

    private ResponseEntity<?> join(UserJoinRequest joinRequest, String email) {
        joinRequest.setEmail(email);
        System.out.println(joinRequest);
        UserJoinResponse joinResponse = userService.join(joinRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("message","로그인이 정상적으로 처리되었습니다.");
        response.put("statusCode", 200);
        response.put("data", joinResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(HttpServletRequest request, UserEditRequest userEditRequest) throws Exception {

        TokenService.isMatched(userEditRequest.getId(), Long.parseLong(request.getAttribute("id").toString()));
        userService.edit(userEditRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("message","회원 수정이 정상적으로 처리되었습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(HttpServletRequest request, @PathVariable Long id) throws Exception {

        TokenService.isMatched(id, Long.parseLong(request.getAttribute("id").toString()));
        userService.destroy(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message","회원 탈퇴가 정상적으로 처리되었습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-duplicate-nickname/{nickname}")
    public ResponseEntity<?> verifyDuplicateNickname(UserVerifyDuplicateNicknameRequest userVerifyDuplicateNicknameRequest) {
        Boolean result = userService.verifyDuplicateNickname(userVerifyDuplicateNicknameRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("available", result);
        response.put("message", result ? "사용가능한 닉네임 입니다.": "중복된 닉네임 입니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-duplicate-email/{email}")
    public ResponseEntity<?> verifyDuplicateEmail(UserVerifyDuplicateEmailRequest userVerifyDuplicateEmailRequest) {
        Boolean result = userService.verifyDuplicateEmail(userVerifyDuplicateEmailRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("available", result);
        response.put("message", result ? "사용가능한 이메일 입니다.": "중복된 이메일 입니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        UserShowResponse userShowResponse = userService.show(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message","회원 상세정보를 성공적으로 가져왔습니다.");
        response.put("user", userShowResponse);
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }
}
