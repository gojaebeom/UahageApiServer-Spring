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
public class PlaceRestaurantBookmark {
    private Long id;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
