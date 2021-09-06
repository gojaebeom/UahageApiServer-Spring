package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PlaceRestaurantReview {
    private Long id;
    private String description;
    private Float totalRating;
    private Float tasteRating;
    private Float costRating;
    private Float serviceRating;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
