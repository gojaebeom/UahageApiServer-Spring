package com.uahage.api.mapper;

import com.uahage.api.dto.PlaceRestaurantReviewImagesResponse;
import com.uahage.api.dto.PlaceRestaurantReviewsRequest;
import com.uahage.api.dto.PlaceRestaurantReviewsResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceRestaurantReviewMapper {
    List<PlaceRestaurantReviewsResponse> findAllByReviewIdWithOrderByOption(PlaceRestaurantReviewsRequest restaurantReviewsRequest);
    List<PlaceRestaurantReviewImagesResponse> findImagesByRestaurantId(Long placeId);
    PlaceRestaurantReviewsResponse findOneById(Long reviewId);
}
