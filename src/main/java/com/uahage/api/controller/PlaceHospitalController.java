package com.uahage.api.controller;

import com.uahage.api.dto.PlaceCommonListResponse;
import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.service.PlaceHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/places/hospitals")
public class PlaceHospitalController {

    private PlaceHospitalService placeHospitalService;

    @GetMapping("")
    public ResponseEntity<?> index(PlaceCommonRequest placeCommonRequest){
        List<PlaceCommonListResponse> placeCommonListResponseList =  placeHospitalService.findAll(placeCommonRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("message","병원 리스트 데이터를 성공적으로 가져왔습니다.");
        response.put("statusCode", 200);
        response.put("hospitals", placeCommonListResponseList);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> show(Long id){
        return null;
    }
}
