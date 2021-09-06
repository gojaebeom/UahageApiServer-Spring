package com.uahage.api.dto;

import com.uahage.api.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPlaceRestaurantDetailDto {

    private PlaceRestaurant restaurant;
    private PlaceRestaurantFacility facility;
    private PlaceRestaurantInfo info;

    private Boolean isBookmarked;
    private Float reviewTotal;

    private List<PlaceRestaurantImage> images;
    private List<PlaceRestaurantMenu> menus;
}
