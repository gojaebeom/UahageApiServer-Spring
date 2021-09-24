package com.uahage.api.controller;

import com.uahage.api.dto.PlaceRestaurantReviewImagesResponse;
import com.uahage.api.dto.PlaceRestaurantReviewStoreRequest;
import com.uahage.api.dto.PlaceRestaurantReviewsRequest;
import com.uahage.api.dto.PlaceRestaurantReviewsResponse;
import com.uahage.api.service.PlaceRestaurantReviewService;
import com.uahage.api.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/places/restaurants/reviews")
@Slf4j
@AllArgsConstructor
public class PlaceRestaurantReviewController {

    private PlaceRestaurantReviewService restaurantReviewService;

    @GetMapping("")
    public ResponseEntity<?> index(PlaceRestaurantReviewsRequest restaurantReviewsRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        if(restaurantReviewsRequest.isOnlyImage()){
            List<PlaceRestaurantReviewImagesResponse> restaurantReviewImagesResponses = restaurantReviewService.indexOnlyImage(restaurantReviewsRequest);
            response.put("reviews", restaurantReviewImagesResponses);
        }else{
            List<PlaceRestaurantReviewsResponse> restaurantReviewsResponses = restaurantReviewService.index(restaurantReviewsRequest);
            HashMap shared = restaurantReviewService.shared(restaurantReviewsResponses);
            response.put("reviews", restaurantReviewsResponses);
            response.put("shared", shared);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> show(@PathVariable Long reviewId){
        PlaceRestaurantReviewsResponse restaurantReviewsResponse = restaurantReviewService.show(reviewId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("review", restaurantReviewsResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> store(HttpServletRequest request, PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest) throws Exception {

        TokenService.isMatched(restaurantReviewStoreRequest.getUserIdOrThrowException(), Long.parseLong(request.getAttribute("id").toString()));

        System.out.println(restaurantReviewStoreRequest);
        restaurantReviewService.store(restaurantReviewStoreRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 저장했습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> edit(HttpServletRequest request, PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest) throws Exception {

        TokenService.isMatched(restaurantReviewStoreRequest.getUserIdOrThrowException(), Long.parseLong(request.getAttribute("id").toString()));

        System.out.println(restaurantReviewStoreRequest);
        restaurantReviewService.edit(restaurantReviewStoreRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 수정했습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> destroy(HttpServletRequest request, @PathVariable Long reviewId) throws Exception {

        Long userId = restaurantReviewService.findUserIdByReviewId(reviewId);
        TokenService.isMatched(userId, Long.parseLong(request.getAttribute("id").toString()));

        restaurantReviewService.destroy(reviewId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "데이터를 성공적으로 제거했습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }
}
