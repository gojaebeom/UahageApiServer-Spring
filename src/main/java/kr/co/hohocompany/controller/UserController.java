package kr.co.hohocompany.controller;

import kr.co.hohocompany.dto.ReqEditUserDto;
import kr.co.hohocompany.dto.ReqJoinDto;
import kr.co.hohocompany.dto.ResJoinDto;
import kr.co.hohocompany.dto.ResShowUserDto;
import kr.co.hohocompany.service.Oauth2LoginService;
import kr.co.hohocompany.service.UserService;
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
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
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
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try{
            userService.destroy(id);

            response.put("message","회원 탈퇴가 정상적으로 처리되었습니다.");
            response.put("statusCode", 200);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/verify-duplicate-nickname/{nickname}")
    public ResponseEntity verifyDuplicateNickname(@PathVariable String nickname) {
        Map<String, Object> response = new HashMap<>();
        try{
            Boolean result = userService.verifyDuplicateNickname(nickname);

            String message = result ? "사용가능한 닉네임 입니다." : "중복된 닉네임 입니다";

            response.put("message", message);
            response.put("statusCode", 200);
            response.put("available", result);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/verify-duplicate-email/{email}")
    public ResponseEntity verifyDuplicateEmail(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        try{
            Boolean result = userService.verifyDuplicateEmail(email);

            String message = result ? "사용가능한 이메일 입니다." : "중복된 이메일 입니다";

            response.put("message", message);
            response.put("statusCode", 200);
            response.put("available", result);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try{
            ResShowUserDto resUserShowDto = userService.show(id);

            response.put("message", "회원 상세정보를 성공적으로 가져왔습니다.");
            response.put("statusCode", 200);
            response.put("user", resUserShowDto);
            return ResponseEntity.ok(response);
        }catch(Exception e){

            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
