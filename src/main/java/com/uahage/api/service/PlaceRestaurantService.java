package com.uahage.api.service;

import com.uahage.api.dto.*;
import com.uahage.api.mapper.PlaceRestaurantMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PlaceRestaurantService {

    private PlaceRestaurantMapper placeRestaurantMapper;

    public List<PlaceRestaurantMapResponse> findAllByOptionsTypeMap(PlaceRestaurantsRequest placeRestaurantsRequest) {
        return placeRestaurantMapper.findAllByOptionsTypeMap(placeRestaurantsRequest);
    }

    public List<PlaceRestaurantListResponse> findAllByOptions(PlaceRestaurantsRequest placeRestaurantsRequest){
        return placeRestaurantMapper.findAllByOptions(placeRestaurantsRequest);
    }

    public PlaceRestaurantDetailResponse findOneById(PlaceRestaurantDetailRequest placeRestaurantDetailRequest){
        return placeRestaurantMapper.findOneByIdWithOptionalUserId(placeRestaurantDetailRequest);
    }
}
