package com.uahage.api.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaceRestaurantReview {

    private Long id;
    private User user;
    private PlaceRestaurant restaurant;
    private String description;
    private Float totalRating;
    private Float tasteRating;
    private Float costRating;
    private Float serviceRating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
