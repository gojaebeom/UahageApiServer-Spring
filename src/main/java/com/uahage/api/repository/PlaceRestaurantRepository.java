package com.uahage.api.repository;

import com.uahage.api.domain.PlaceRestaurant;
import com.uahage.api.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PlaceRestaurantRepository {

    List<ResPlaceRestaurantDto> findAllByOptions(ReqPlaceRestaurantDto reqPlaceRestaurantDto);

    ResPlaceRestaurantDetailDto findOneByIdWithOptionalUserId(ReqPlaceRestaurantDetailDto reqPlaceRestaurantDetailDto);
}
