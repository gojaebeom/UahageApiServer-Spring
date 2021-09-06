package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurantInfo {
    private Long id;
    private String startTime;
    private String endTime;
    private String closeDay;
    private String memo;
    @JsonIgnore
    private PlaceRestaurant restaurant;
}
