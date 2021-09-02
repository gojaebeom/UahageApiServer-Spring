package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantFacility {

    private Long id;
    private PlaceRestaurant restaurant;
    private Boolean babyMenu;
    private Boolean babyBed;
    private Boolean babyTableware;
    private Boolean babyChair;
    private Boolean diaperChange;
    private Boolean stroller;
    private Boolean meetingRoom;
    private Boolean nursingRoom;
    private Boolean parking;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
