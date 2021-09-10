package com.uahage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class UserVerifyDuplicateEmailRequest {
    private String email;

    public void verifyEmail() {
        log.info("[이메일 유효성 검사]");
        log.info(this.email);
        if(this.email == null){
            throw new IllegalArgumentException("이메일이 입력되지 않았습니다.");
        }
        log.info("[이메일 Null 검사 : Pass]");

        final String PATTERN = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$";
        String email = "";
        try{
             email = this.email.split(":")[1];
        }catch(Exception e){
            throw new IllegalArgumentException("소셜 구분이 정의되지 않았습니다.");
        }

        if(!email.matches(PATTERN)){
            throw new IllegalArgumentException("이메일 형식이 아닙니다.");
        }
        log.info("[이메일 정규식 검사 : Pass]");
    }
}
