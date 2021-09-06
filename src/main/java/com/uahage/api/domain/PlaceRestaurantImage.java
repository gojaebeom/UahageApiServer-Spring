package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantImage {
    private Long id;
    private String imagePath;
    private String previewImagePath;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
