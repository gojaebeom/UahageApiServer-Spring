package com.uahage.api.mapper;

import com.uahage.api.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceRestaurantMapper {

    PlaceRestaurantDetailResponse findOneByIdWithOptionalUserId(PlaceRestaurantDetailRequest placeRestaurantRequest);

    List<PlaceRestaurantListResponse> findAllByOptions(PlaceRestaurantsRequest placeRestaurantsRequest);

    List<PlaceRestaurantMapResponse> findAllByOptionsTypeMap(PlaceRestaurantsRequest placeRestaurantsRequest);
}
