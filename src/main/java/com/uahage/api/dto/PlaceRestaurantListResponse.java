package com.uahage.api.dto;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class PlaceRestaurantListResponse {
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
    // facility
    private HashMap<String,Object> facility;
    // image
    private HashMap<String,Object> image;
}
