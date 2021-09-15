package com.uahage.api.mapper;

import com.uahage.api.dto.PlaceCommonRequest;
import com.uahage.api.dto.PlaceCommonResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceExperienceCenterMapper {
    List<PlaceCommonResponse> findAllByOptions(PlaceCommonRequest placeDefaultRequest);

    List<PlaceCommonResponse> findMapByOptions(PlaceCommonRequest placeDefaultRequest);

    PlaceCommonResponse findOneById(Long id);
}
