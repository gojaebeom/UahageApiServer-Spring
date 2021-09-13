package com.uahage.api.controller;

import com.uahage.api.dto.PlaceRestaurantsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PlaceRestaurantControllerTest {

    @Autowired
    private PlaceRestaurantController placeRestaurantController;

    @Test
    public void indexTest(){
        PlaceRestaurantsRequest placeRestaurantsRequest = PlaceRestaurantsRequest.builder()
                .lat(35.1449589F)
                .lon(126.9216603F)
                .userId(12L)
//                .babyBed(true)
//                .babyChair(true)
//                .babyMenu(false)
//                .babyTableware(true)
//                .stroller(false)
//                .diaperChange(false)
//                .meetingRoom(false)
//                .nursingRoom(false)
//                .playRoom(false)
//                .parking(false)
                .pageNumber(1)
                .build();
        ResponseEntity responseEntity  = placeRestaurantController.index(placeRestaurantsRequest);
        HashMap<String, Object> result = (HashMap<String, Object>) responseEntity.getBody();
        assertTrue(result.get("statusCode").toString().equals("200"));
    }

    @Test
    public void showTest(){

    }
}
