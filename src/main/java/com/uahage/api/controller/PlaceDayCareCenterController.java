package com.uahage.api.controller;

import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonResponse;
import com.uahage.api.service.PlaceDayCareCenterService;
import com.uahage.api.service.PlaceHospitalService;
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
@RequestMapping("/places/day-care-centers")
@Slf4j
@AllArgsConstructor
public class PlaceDayCareCenterController {
    private PlaceDayCareCenterService placeDayCareCenterService;

    @GetMapping("")
    public ResponseEntity<?> index(PlaceCommonRequest placeCommonRequest){
        log.info("요청 옴!");
        System.out.println(placeCommonRequest);
        List<PlaceCommonResponse> placeCommonResponses =  placeDayCareCenterService.findAll(placeCommonRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("message","데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("places", placeCommonResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        PlaceCommonResponse placeCommonResponse =  placeDayCareCenterService.findOne(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message","데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("place", placeCommonResponse);
        return ResponseEntity.ok(response);
    }
}
