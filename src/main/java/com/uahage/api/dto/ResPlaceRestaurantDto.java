package com.uahage.api.dto;

import lombok.*;

@Data
@Builder
public class ResPlaceRestaurantDto {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private Float reviewTotal;
    private Long bookmark;
    private Boolean babyBed;
    private Boolean babyChair;
    private Boolean babyMenu;
    private Boolean babyTableware;
    private Boolean stroller;
    private Boolean diaperChange;
    private Boolean meetingRoom;
    private Boolean nursingRoom;
    private Boolean parking;
}
