package com.uahage.api.service;

import com.uahage.api.dto.PlaceCommonListResponse;
import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.mapper.PlaceHospitalMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class PlaceHospitalService {

    private PlaceHospitalMapper placeHospitalMapper;

    public List<PlaceCommonListResponse> findAll(PlaceCommonRequest placeCommonRequest) {
        if(placeCommonRequest.isMap()){
            return placeHospitalMapper.findMapByOptions(placeCommonRequest);
        }
        return placeHospitalMapper.findAllByOptions(placeCommonRequest);
    }
}
