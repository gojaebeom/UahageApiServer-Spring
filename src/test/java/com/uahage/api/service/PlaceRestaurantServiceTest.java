package com.uahage.api.service;

import com.uahage.api.dto.ReqPlaceRestaurantDto;
import com.uahage.api.dto.ResPlaceRestaurantDetailDto;
import com.uahage.api.dto.ResPlaceRestaurantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class PlaceRestaurantServiceTest {

    @Autowired
    PlaceRestaurantService placeRestaurantService;

    @Test
    public void indexTest(){
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
        List<ResPlaceRestaurantDto> resPlaceRestaurantDtos = placeRestaurantService.findAllOrOptions(reqPlaceRestaurantDto);
        System.out.println(resPlaceRestaurantDtos);
    }

    @Test
    public void showTest(){
        HashMap<String, Object> maps  = placeRestaurantService.findOneById(288L);
        System.out.println(maps);
    }
}
