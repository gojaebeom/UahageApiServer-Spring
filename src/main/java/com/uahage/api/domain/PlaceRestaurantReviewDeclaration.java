package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantReviewDeclaration {
    private Long id;
    private String description;
    @JsonIgnore
    private PlaceRestaurantReviewDeclarationCategory category;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private PlaceRestaurantReview review;
}
