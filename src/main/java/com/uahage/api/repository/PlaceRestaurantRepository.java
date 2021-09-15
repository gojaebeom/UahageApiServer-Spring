package com.uahage.api.repository;

import com.uahage.api.domain.place.PlaceRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRestaurantRepository extends JpaRepository<PlaceRestaurant, Long> {
    PlaceRestaurant findOneById(Long placeIdOrThrowException);
}
