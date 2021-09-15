package com.uahage.api.service;


import com.uahage.api.controller.PlaceExperienceCenterController;
import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonResponse;
import com.uahage.api.mapper.PlaceExperienceCenterMapper;
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
public class PlaceExperienceCenterService {

    private PlaceExperienceCenterMapper placeExperienceCenterMapper;

    public List<PlaceCommonResponse> findAll(PlaceCommonRequest placeCommonRequest) {
        log.info("요청옴");
        if(placeCommonRequest.isMap()){
            return placeExperienceCenterMapper.findMapByOptions(placeCommonRequest);
        }
        return placeExperienceCenterMapper.findAllByOptions(placeCommonRequest);
    }

    public PlaceCommonResponse findOne(Long id) {
        return placeExperienceCenterMapper.findOneById(id);
    }
}
