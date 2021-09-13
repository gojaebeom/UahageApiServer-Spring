package com.uahage.api.controller;

import com.uahage.api.dto.*;
import com.uahage.api.service.PlaceRestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/places/restaurants")
@AllArgsConstructor
@Slf4j
public class PlaceRestaurantController {

    private PlaceRestaurantService placeRestaurantService;

    @GetMapping("")
    public ResponseEntity<?> index(PlaceRestaurantsRequest placeRestaurantsRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "음식점, 카페 목록을 성공적으로 가져왔습니다");
        response.put("statusCode", 200);
        if(placeRestaurantsRequest.isMap()){
            List<PlaceRestaurantMapResponse> placeRestaurantMapResponses = placeRestaurantService.findAllByOptionsTypeMap(placeRestaurantsRequest);
            response.put("places", placeRestaurantMapResponses);
        }else{
            List<PlaceRestaurantListResponse> placeRestaurantsResponses = placeRestaurantService.findAllByOptions(placeRestaurantsRequest);
            response.put("places", placeRestaurantsResponses);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id, PlaceRestaurantDetailRequest placeRestaurantDetailRequest){
        System.out.println(id);
        System.out.println(placeRestaurantDetailRequest);
        PlaceRestaurantDetailResponse placeRestaurantDetailResponse = placeRestaurantService.findOneById(placeRestaurantDetailRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "음식점, 카페 상세정보를 성공적으로 가져왔습니다");
        response.put("statusCode", 200);
        response.put("place", placeRestaurantDetailResponse);
        return ResponseEntity.ok(response);
    }
}
