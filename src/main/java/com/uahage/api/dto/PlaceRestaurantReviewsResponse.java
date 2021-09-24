package com.uahage.api.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@ToString
public class PlaceRestaurantReviewsResponse {
    private Long id;
    private String desc;
    private Float totalRating;
    private Float tasteRating;
    private Float costRating;
    private Float serviceRating;
    private String updatedAt;
    private HashMap<String, Object> user;
    private List<HashMap<String, Object>> images;
}
