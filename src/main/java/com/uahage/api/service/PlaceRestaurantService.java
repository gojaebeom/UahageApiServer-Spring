package com.uahage.api.service;

import com.uahage.api.dto.*;
import com.uahage.api.mapper.PlaceRestaurantMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PlaceRestaurantService {

    private PlaceRestaurantMapper placeRestaurantMapper;

    public List<PlaceRestaurantMapResponse> findAllByOptionsTypeMap(PlaceRestaurantsRequest placeRestaurantsRequest) {
        return placeRestaurantMapper.findAllByOptionsTypeMap(placeRestaurantsRequest);
    }

    public List<PlaceRestaurantListResponse> findAllByOptions(PlaceRestaurantsRequest placeRestaurantsRequest){
        return placeRestaurantMapper.findAllByOptions(placeRestaurantsRequest);
    }

    public PlaceRestaurantDetailResponse findOneById(PlaceRestaurantDetailRequest placeRestaurantDetailRequest){
        return placeRestaurantMapper.findOneByIdWithOptionalUserId(placeRestaurantDetailRequest);
    }

    public Boolean bookmarked(PlaceRestaurantBookmarkRequest placeRestaurantBookmarkRequest) {
        placeRestaurantBookmarkRequest.verifyAll();

        Long bookmarkId = placeRestaurantMapper.findBookmarkIdByPlaceIdWithUserId(placeRestaurantBookmarkRequest);
        log.info("[ 북마크 아이디 확인 ]");
        System.out.println(bookmarkId);
        if( bookmarkId != null ){
            placeRestaurantMapper.deleteBookmark(bookmarkId);
            return false;
        }else{
            placeRestaurantMapper.saveBookmark(placeRestaurantBookmarkRequest);
            return true;
        }
    }
}
