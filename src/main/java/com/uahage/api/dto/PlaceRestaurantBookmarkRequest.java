package com.uahage.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class PlaceRestaurantBookmarkRequest {
    private Long placeId;
    private Long userId;

    public void verifyAll() {
        log.info("[ 북마크 유효성 검사 ]");

        if(this.placeId <= 0 || this.placeId == null){
            throw new IllegalArgumentException("장소 ID가 없습니다");
        }
        log.info("[ 장소 id : PASS ]");
        if(this.userId <= 0 || this.userId == null){
            throw new IllegalArgumentException("회원 ID가 없습니다");
        }
        log.info("[ 회원 id : PASS ]");
    }
}
