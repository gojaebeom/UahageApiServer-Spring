package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRestaurant {
    private Long id;
    private Long categoryId;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
}
