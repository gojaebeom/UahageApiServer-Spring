package com.uahage.api.service;

import com.uahage.api.dto.ReqPlaceRestaurantDto;
import com.uahage.api.dto.ResPlaceRestaurantDetailDto;
import com.uahage.api.dto.ResPlaceRestaurantDto;
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

    public HashMap<String, Object> findOneById(Long id){
        return placeRestaurantRepository.findOneById(id);
    }
}
