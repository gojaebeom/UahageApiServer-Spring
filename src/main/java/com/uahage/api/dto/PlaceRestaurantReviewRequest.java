package com.uahage.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlaceRestaurantReviewRequest {
    private Long placeId; // 필수값
    private String type;
    private String order;
}
