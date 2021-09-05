package com.uahage.api.dto;

import com.uahage.api.domain.PlaceRestaurantImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPlaceRestaurantDetailDto {
    // 기본 레스토랑
    private Long id;
    private String name;
    private String address;
    private String phone;
    // 유아 관련 정보
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
    // 레스토랑 정보
    private String startTime;
    private String endTime;
    private String closeDay;
    private String memo;
    // 북마크 상태
    private Boolean isBookmarked;
    // 리뷰 총점
    private Float reviewTotal;
    // 이미지 리스트
    private List<HashMap<String, Object>> images;
    // 매뉴 리스트
    private List<HashMap<String ,Object>> menus;
}
