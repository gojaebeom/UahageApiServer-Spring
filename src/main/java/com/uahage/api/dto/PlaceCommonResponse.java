package com.uahage.api.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class PlaceCommonResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private HashMap<String, Object> info;
    private List<HashMap<String,Object>> images;
}
