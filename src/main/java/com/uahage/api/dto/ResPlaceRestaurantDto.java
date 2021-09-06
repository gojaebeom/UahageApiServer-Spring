package com.uahage.api.dto;

import com.uahage.api.domain.PlaceRestaurant;
import com.uahage.api.domain.PlaceRestaurantFacility;
import com.uahage.api.domain.PlaceRestaurantImage;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPlaceRestaurantDto {
    private PlaceRestaurant restaurant;
    private PlaceRestaurantFacility facility;
    private PlaceRestaurantImage image;
    private Float reviewTotal;
    private Boolean isBookmarked;
}
