package com.uahage.api.controller;

import com.uahage.api.dto.PlaceInfoProposeStoreRequest;
import com.uahage.api.service.PlaceInfoProposeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/places/proposes")
@AllArgsConstructor
@Slf4j
public class PlaceInfoProposeController {

    private PlaceInfoProposeService infoProposeService;

    @PostMapping("")
    public ResponseEntity<?> store(PlaceInfoProposeStoreRequest infoProposeStoreRequest) throws Exception {
        System.out.println(infoProposeStoreRequest);
        infoProposeService.store(infoProposeStoreRequest);
        HashMap<String, Object> response = new HashMap<>();
        response.put("massage", "정보수정이 제안되었습니다.");
        response.put("statusCode", 200);
        return ResponseEntity.ok().body(response);
    }
}
