package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantReviewImage {

    private Long id;
    private PlaceRestaurant restaurant;
    private String originPath;
    private String previewPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}