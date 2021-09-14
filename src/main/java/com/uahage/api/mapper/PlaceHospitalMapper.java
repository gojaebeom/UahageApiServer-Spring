package com.uahage.api.mapper;

import com.uahage.api.dto.PlaceCommonDetailResponse;
import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceHospitalMapper {

    List<PlaceCommonListResponse> findAllByOptions(PlaceCommonRequest placeDefaultRequest);

    List<PlaceCommonListResponse> findMapByOptions(PlaceCommonRequest placeDefaultRequest);

    PlaceCommonDetailResponse findOneById(Long id);
}
