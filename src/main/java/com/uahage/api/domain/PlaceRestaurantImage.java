package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantImage {

    private Long id;
    private String imagePath;
    private String previewImagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PlaceRestaurant restaurant;
}
