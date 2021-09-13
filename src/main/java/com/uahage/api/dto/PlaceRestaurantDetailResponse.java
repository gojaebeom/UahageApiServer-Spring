package com.uahage.api.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@ToString
public class PlaceRestaurantDetailResponse {
    // default
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private Long categoryId;
    private Boolean isBookmarked;
    private Float reviewTotal;
    // info
    private HashMap<String, Object> info;
    // facility
    private HashMap<String, Object> facility;
    // menu
    private List<HashMap<String,Object>> menu;
    // images
    private List<HashMap<String,Object>> images;

}
