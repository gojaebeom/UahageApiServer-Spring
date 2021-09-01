package kr.co.hohocompany.repository;

import kr.co.hohocompany.domain.place.PlaceRestaurantFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRestaurantFacilityRepository extends JpaRepository<PlaceRestaurantFacility, Long> {
    PlaceRestaurantFacility findOneByRestaurantId(Long id);
}
