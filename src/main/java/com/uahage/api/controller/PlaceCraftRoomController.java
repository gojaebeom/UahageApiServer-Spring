package com.uahage.api.controller;

import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonResponse;
import com.uahage.api.service.PlaceCraftRoomService;
import com.uahage.api.service.PlaceExperienceCenterService;
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
@RequestMapping("/places/craft-rooms")
@Slf4j
@AllArgsConstructor
public class PlaceCraftRoomController {
    private PlaceCraftRoomService placeCraftRoomService;

    @GetMapping("")
    public ResponseEntity<?> index(PlaceCommonRequest placeCommonRequest){
        log.info("요청 옴!");
        System.out.println(placeCommonRequest);
        List<PlaceCommonResponse> placeCommonResponses =  placeCraftRoomService.findAll(placeCommonRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("message","데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("places", placeCommonResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        PlaceCommonResponse placeCommonResponse =  placeCraftRoomService.findOne(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message","데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("place", placeCommonResponse);
        return ResponseEntity.ok(response);
    }
}
