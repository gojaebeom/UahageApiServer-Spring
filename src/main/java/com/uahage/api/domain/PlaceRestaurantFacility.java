package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantFacility {
    private Long id;
    private Boolean babyMenu;
    private Boolean babyBed;
    private Boolean babyTableware;
    private Boolean babyChair;
    private Boolean diaperChange;
    private Boolean stroller;
    private Boolean meetingRoom;
    private Boolean nursingRoom;
    private Boolean playRoom;
    private Boolean parking;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
