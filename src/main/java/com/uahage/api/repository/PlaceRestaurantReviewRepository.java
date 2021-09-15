package com.uahage.api.repository;

import com.uahage.api.domain.place.PlaceRestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRestaurantReviewRepository extends JpaRepository<PlaceRestaurantReview, Long> {

    PlaceRestaurantReview findOneById(Long placeIdOrThrowException);
}
