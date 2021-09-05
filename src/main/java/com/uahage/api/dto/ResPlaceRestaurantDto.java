package com.uahage.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPlaceRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    // 아기 관련 정보
    private Boolean babyBed;
    private Boolean babyChair;
    private Boolean babyMenu;
    private Boolean babyTableware;
    private Boolean stroller;
    private Boolean diaperChange;
    private Boolean meetingRoom;
    private Boolean nursingRoom;
    private Boolean playRoom;
    private Boolean parking;
    // 썸네일
    private String imagePath;
    // 리뷰 토탈
    private Float reviewTotal;
    // 북마크 상태
    private Boolean isBookmarked;
}
