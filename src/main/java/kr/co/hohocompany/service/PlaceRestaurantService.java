package kr.co.hohocompany.service;

import kr.co.hohocompany.dto.ResPlaceRestaurantDto;
import kr.co.hohocompany.repository.PlaceRestaurantFacilityRepository;
import kr.co.hohocompany.repository.PlaceRestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PlaceRestaurantService {

    @Autowired
    private PlaceRestaurantRepository placeRestaurantRepository;
    private PlaceRestaurantFacilityRepository placeRestaurantFacilityRepository;

    public List<ResPlaceRestaurantDto> findAll() {
        List<ResPlaceRestaurantDto> placeRestaurants = placeRestaurantRepository.findAllByWithRestaurantFacility();

        System.out.println(placeRestaurants.size());

//        for(ResPlaceRestaurantDto ResPlaceRestaurantDto : placeRestaurants){
//            System.out.println("------------------------------------------");
//            System.out.println(ResPlaceRestaurantDto);
//        }
        return placeRestaurants;
    }
}
