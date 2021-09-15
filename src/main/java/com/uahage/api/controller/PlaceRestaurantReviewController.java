package com.uahage.api.controller;

import com.uahage.api.dto.PlaceRestaurantReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/places/restaurants/reviews")
@Slf4j
public class PlaceRestaurantReviewController {

    @GetMapping("")
    public ResponseEntity<?> index(PlaceRestaurantReviewRequest restaurantReviewRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "음식점, 카페 상세정보를 성공적으로 가져왔습니다");
        response.put("statusCode", 200);
        response.put("reviews", restaurantReviewRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> show(@PathVariable Long placeId, @PathVariable Long reviewId){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "음식점, 카페 상세정보를 성공적으로 가져왔습니다");
        response.put("statusCode", 200);
        response.put("reviews", placeId);
        return ResponseEntity.ok(response);
    }
}
