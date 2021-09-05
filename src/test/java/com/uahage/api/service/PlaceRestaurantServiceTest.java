package com.uahage.api.service;

import com.uahage.api.domain.PlaceRestaurant;
import com.uahage.api.dto.*;
import com.uahage.api.repository.PlaceRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Transactional
public class PlaceRestaurantServiceTest {

    @Autowired
    PlaceRestaurantService placeRestaurantService;

    @Autowired
    PlaceRestaurantRepository placeRestaurantRepository;

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
        ReqPlaceRestaurantDetailDto reqPlaceRestaurantDetailDto = new ReqPlaceRestaurantDetailDto(288L, 10L);

        ResPlaceRestaurantDetailDto resPlaceRestaurantDetailDto  = placeRestaurantService.findOneByIdAndUserId(reqPlaceRestaurantDetailDto);
        System.out.println(resPlaceRestaurantDetailDto);
    }

    @Test
    public void joinTest() {
       PlaceRestaurant placeRestaurant = placeRestaurantRepository.findTestJoin(288L);
        System.out.println(placeRestaurant);
    }


}
