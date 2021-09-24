package com.uahage.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlaceRestaurantReviewsRequest {
    private Long placeId; // 필수값
    private String type;
    private String order;

    public Boolean isOnlyImage(){
        if(this.type == null){
            return false;
        }
        if(this.type.equals("img")){
            return true;
        }
        return false;
    }
}