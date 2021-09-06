package com.uahage.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/login")
public class OauthLoginTestController {

    @GetMapping("")
    public String authLoginView(){
        return "login";
    }

    @GetMapping("/kakao")
    public String redirectKakaoView(){
        return "redirect-kakao";
    }

    @GetMapping("/naver")
    public String redirectNaverView(){
        return "redirect-naver";
    }
}
