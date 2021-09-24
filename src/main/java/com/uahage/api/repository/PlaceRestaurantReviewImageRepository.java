package com.uahage.api.repository;

import com.uahage.api.domain.place.PlaceRestaurantReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRestaurantReviewImageRepository extends JpaRepository<PlaceRestaurantReviewImage, Long> {
    List<PlaceRestaurantReviewImage> findAllByReviewId(Long reviewId);
    PlaceRestaurantReviewImage findOneById(Long id);
}
