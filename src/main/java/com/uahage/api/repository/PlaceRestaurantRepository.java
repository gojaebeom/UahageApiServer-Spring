package com.uahage.api.repository;

import com.uahage.api.dto.ReqPlaceRestaurantDto;
import com.uahage.api.dto.ResPlaceRestaurantDetailDto;
import com.uahage.api.dto.ResPlaceRestaurantDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PlaceRestaurantRepository {

    List<ResPlaceRestaurantDto> findAllOrOptions(ReqPlaceRestaurantDto reqPlaceRestaurantDto);

    HashMap<String, Object> findOneById(Long id);
}
