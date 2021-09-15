package com.uahage.api.service;


import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonResponse;
import com.uahage.api.mapper.PlaceDayCareCenterMapper;
import com.uahage.api.mapper.PlaceKidCafeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Transactional
@Slf4j
@Service
public class PlaceKidCafeService {

    private PlaceKidCafeMapper placeKidCafeMapper;

    public List<PlaceCommonResponse> findAll(PlaceCommonRequest placeCommonRequest) {
        log.info("요청옴");
        if(placeCommonRequest.isMap()){
            return placeKidCafeMapper.findMapByOptions(placeCommonRequest);
        }
        return placeKidCafeMapper.findAllByOptions(placeCommonRequest);
    }

    public PlaceCommonResponse findOne(Long id) {
        return placeKidCafeMapper.findOneById(id);
    }
}
