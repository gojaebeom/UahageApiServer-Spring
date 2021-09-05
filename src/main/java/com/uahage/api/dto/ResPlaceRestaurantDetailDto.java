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
    private Integer id;
    private String name;
    private String address;
    private String phone;
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
    private Boolean playRoom;
    private Boolean parking;
    private List<String> images;
    private List<HashMap<String ,String>> menus;
}
