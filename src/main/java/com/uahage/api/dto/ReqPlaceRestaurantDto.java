package com.uahage.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ReqPlaceRestaurantDto {
    private Float lat;
    private Float lon;
    private Long userId;
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
    private Boolean isBookmarked;
    private Integer pageNumber;

    public void setPageNumber(Integer pageNumber) {
        // 1 : 1 ~ 10
        // 2 : 11 ~ 20
        System.out.println(((pageNumber-1) * 10)+1);
        this.pageNumber = pageNumber <= 1 ? pageNumber : ((pageNumber-1) * 10)+1;
    }

    @Builder
    public ReqPlaceRestaurantDto(Float lat, Float lon, Long userId, Boolean babyBed, Boolean babyChair, Boolean babyMenu, Boolean babyTableware, Boolean stroller, Boolean diaperChange, Boolean meetingRoom, Boolean nursingRoom, Boolean playRoom, Boolean parking, Boolean isBookmarked, Integer pageNumber) {
        this.lat = lat;
        this.lon = lon;
        this.userId = userId;
        this.babyBed = babyBed;
        this.babyChair = babyChair;
        this.babyMenu = babyMenu;
        this.babyTableware = babyTableware;
        this.stroller = stroller;
        this.diaperChange = diaperChange;
        this.meetingRoom = meetingRoom;
        this.nursingRoom = nursingRoom;
        this.playRoom = playRoom;
        this.parking = parking;
        this.isBookmarked = isBookmarked;
        this.pageNumber = pageNumber;
    }
}