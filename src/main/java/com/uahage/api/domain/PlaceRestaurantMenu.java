package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantMenu {
    private Long id;
    private String name;
    private Integer price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PlaceRestaurant restaurant;
}
