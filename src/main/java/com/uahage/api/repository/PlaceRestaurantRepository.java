package com.uahage.api.repository;

import com.uahage.api.domain.PlaceRestaurant;
import com.uahage.api.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PlaceRestaurantRepository {

    List<ResPlaceRestaurantDto> findAllOrOptions(ReqPlaceRestaurantDto reqPlaceRestaurantDto);

    ResPlaceRestaurantDetailDto findOneByIdAndUserId(ReqPlaceRestaurantDetailDto reqPlaceRestaurantDetailDto);

    PlaceRestaurant findTestJoin(Long id);
}
