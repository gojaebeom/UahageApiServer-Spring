package kr.co.hohocompany.uahage.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import kr.co.hohocompany.uahage.domain.user.User;
import kr.co.hohocompany.uahage.dto.ResponseBodyForm;
import kr.co.hohocompany.uahage.dto.UserCreateRequest;
import kr.co.hohocompany.uahage.dto.UserUpdateRequest;
import kr.co.hohocompany.uahage.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/kakao-login")
    public ResponseEntity kakaoLogin(HttpServletRequest request) throws Exception {

        System.out.println(request.getAttribute("email"));

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/users/validate-nickname/{nickname}")
    public ResponseEntity validateNickname(@PathVariable String nickname) throws Exception {

        System.out.println(nickname);

        Boolean result = userService.validateNickname(nickname);

        ResponseBodyForm responseBodyForm = ResponseBodyForm.builder()
                .message(result ? "you can use this nickname" : "you can not use this nickname")
                .build();

        return ResponseEntity.ok().body(responseBodyForm);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity update(UserUpdateRequest userUpdateRequest) throws Exception {

        System.out.println(userUpdateRequest);

        userService.update(userUpdateRequest);

        return ResponseEntity.ok("ok");
    }
}
