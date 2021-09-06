package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantMenu {
    private Long id;
    private String name;
    private String price;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
