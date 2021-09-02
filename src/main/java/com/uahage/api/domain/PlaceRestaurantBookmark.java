package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantBookmark {

    private Long id;
    private User user;
    private PlaceRestaurant restaurant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
