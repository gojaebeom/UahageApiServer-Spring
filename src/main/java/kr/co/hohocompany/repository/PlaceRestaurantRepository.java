package kr.co.hohocompany.repository;

import kr.co.hohocompany.domain.place.PlaceRestaurant;
import kr.co.hohocompany.dto.ResPlaceRestaurantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRestaurantRepository extends JpaRepository<PlaceRestaurant, Long> {

    @Query(
    "select " +
    "new kr.co.hohocompany.dto.ResPlaceRestaurantDto(" +
        "pr.id, " +
        "pr.name, " +
        "pr.address, " +
        "pr.phone, " +
        "pr.lat, " +
        "pr.lon, " +
        "prf.babyBed, " +
        "prf.babyChair, " +
        "prf.babyMenu, " +
        "prf.babyTableware, " +
        "prf.stroller, " +
        "prf.diaperChange, " +
        "prf.meetingRoom, " +
        "prf.nursingRoom, " +
        "prf.parking" +
        ")" +
    "from PlaceRestaurant pr " +
    "left join PlaceRestaurantFacility prf on pr.id = prf.restaurant.id"
    )
    List<ResPlaceRestaurantDto> findAllByWithRestaurantFacility();
}
