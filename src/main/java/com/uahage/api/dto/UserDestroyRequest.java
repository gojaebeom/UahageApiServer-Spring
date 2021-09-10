package com.uahage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class UserDestroyRequest {
    private Long id;

    public Long getIdOrThrowException(){
        log.info("[ 아이디 유효성 검사 ]");
        if(this.id == null) {
            throw new IllegalArgumentException("일치하는 유저가 없습니다");
        }
        log.info("[ 아이디 값 OK ]");
        return this.id;
    }
}
