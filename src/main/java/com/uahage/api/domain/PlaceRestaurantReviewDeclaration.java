package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantReviewDeclaration {

    private Long id;
    private PlaceRestaurantReviewDeclarationCategory category;
    private User user;
    private PlaceRestaurantReview review;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
