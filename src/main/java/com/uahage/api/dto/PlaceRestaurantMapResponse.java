package com.uahage.api.dto;

import lombok.Getter;

@Getter
public class PlaceRestaurantMapResponse {
    private Long id;
    private String name;
    private Float lat;
    private Float lon;
    private Long categoryId;
}
