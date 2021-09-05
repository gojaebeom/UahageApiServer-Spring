package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurant {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PlaceRestaurantFacility facility;
    private List<PlaceRestaurantImage> images;
}
