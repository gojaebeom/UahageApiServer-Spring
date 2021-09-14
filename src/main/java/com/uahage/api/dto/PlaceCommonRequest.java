package com.uahage.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PlaceCommonRequest {
    private Float lat;
    private Float lon;
    private Integer pageNumber;

    public void setPageNumber(Integer pageNumber) {
        // 1 : 1 ~ 10
        // 2 : 11 ~ 20
        System.out.println(((pageNumber-1) * 10)+1);
        this.pageNumber = pageNumber <= 1 ? pageNumber : ((pageNumber-1) * 10)+1;
    }

    public boolean isMap() {
        if(this.pageNumber == null || this.pageNumber <= 0 )
            return true;

        return false;
    }
}
