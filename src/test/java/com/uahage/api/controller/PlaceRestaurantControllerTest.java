package com.uahage.api.controller;

import com.uahage.api.dto.ReqPlaceRestaurantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class PlaceRestaurantControllerTest {

    @Autowired
    private PlaceRestaurantController placeRestaurantController;

    @Test
    public void findAllOrOptionsTest(){
        ReqPlaceRestaurantDto reqPlaceRestaurantDto = ReqPlaceRestaurantDto.builder()
                .lat(22.2F)
                .lon(22.2F)
                .userId(5L)
                .babyBed(true)
                .babyChair(true)
                .babyMenu(false)
                .babyTableware(true)
                .stroller(false)
                .diaperChange(false)
                .meetingRoom(false)
                .nursingRoom(false)
                .playRoom(false)
                .parking(false)
                .build();
        ResponseEntity responseEntity = placeRestaurantController.index(reqPlaceRestaurantDto);
//        System.out.println(responseEntity);
    }
}
