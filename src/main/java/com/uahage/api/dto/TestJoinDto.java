package com.uahage.api.dto;

import com.uahage.api.domain.PlaceRestaurantImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestJoinDto {
    private Long id;
    private String name;
    private Boolean babyBed;
    private List<PlaceRestaurantImage> images;
}
