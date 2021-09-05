package com.uahage.api.service;

import com.uahage.api.dto.*;
import com.uahage.api.repository.PlaceRestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PlaceRestaurantService {

    private PlaceRestaurantRepository placeRestaurantRepository;

    public List<ResPlaceRestaurantDto> findAllOrOptions(ReqPlaceRestaurantDto reqPlaceRestaurantDto){
        return placeRestaurantRepository.findAllOrOptions(reqPlaceRestaurantDto);
    }

    public ResPlaceRestaurantDetailDto findOneByIdAndUserId(ReqPlaceRestaurantDetailDto reqPlaceRestaurantDetailDto){
        return placeRestaurantRepository.findOneByIdAndUserId(reqPlaceRestaurantDetailDto);
    }

}
