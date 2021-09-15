package com.uahage.api.controller;

import com.uahage.api.dto.PlaceRestaurantReviewStoreRequest;
import com.uahage.api.dto.PlaceRestaurantReviewsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/places/restaurants/reviews")
@Slf4j
public class PlaceRestaurantReviewController {

    @GetMapping("")
    public ResponseEntity<?> index(PlaceRestaurantReviewsRequest restaurantReviewRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("reviews", restaurantReviewRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> show(@PathVariable Long reviewId){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("reviews", reviewId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> store(@RequestBody PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest){

        Map<String, Object> response = new HashMap<>();
        response.put("message", "음식점, 카페 상세정보를 성공적으로 가져왔습니다");
        response.put("statusCode", 200);
        response.put("reviews", null);
        return ResponseEntity.ok(response);
    }
}
