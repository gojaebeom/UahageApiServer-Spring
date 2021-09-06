package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String imagePath;
    private String previewImagePath;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
