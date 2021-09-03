package com.uahage.api.controller;

import com.uahage.api.dto.ReqPlaceRestaurantDto;
import com.uahage.api.dto.ResPlaceRestaurantDto;
import com.uahage.api.dto.ResShowUserDto;
import com.uahage.api.service.PlaceRestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<?> index(ReqPlaceRestaurantDto reqPlaceRestaurantDto){

        System.out.println(reqPlaceRestaurantDto);

        Map<String, Object> response = new HashMap<>();
        try{
            List<ResPlaceRestaurantDto> resPlaceRestaurantDtos = placeRestaurantService.findAllOrOptions(reqPlaceRestaurantDto);
            response.put("message", "음식점, 카페 목록을 성공적으로 가져왔습니다");
            response.put("statusCode", 200);
            response.put("places", resPlaceRestaurantDtos);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
            response.put("message", message);
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
