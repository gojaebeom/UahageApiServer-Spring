package kr.co.hohocompany.service;

import kr.co.hohocompany.dto.ResPlaceRestaurantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlaceRestaurantServiceTest {

    @Autowired
    private PlaceRestaurantService placeRestaurantService;

    @Test
    public void findAllTest(){
        List<ResPlaceRestaurantDto> resPlaceRestaurantDtos = placeRestaurantService.findAll();
        System.out.println(resPlaceRestaurantDtos);
    }
}
