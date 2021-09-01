package kr.co.hohocompany.dto;

import kr.co.hohocompany.domain.place.PlaceRestaurant;
import kr.co.hohocompany.domain.place.PlaceRestaurantFacility;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResPlaceRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private Float reviewTotal;
    private Long bookmark;
    private Boolean babyBed;
    private Boolean babyChair;
    private Boolean babyMenu;
    private Boolean babyTableware;
    private Boolean stroller;
    private Boolean diaperChange;
    private Boolean meetingRoom;
    private Boolean nursingRoom;
    private Boolean parking;

    public ResPlaceRestaurantDto(
            Long id,
            String name,
            String address,
            String phone,
            Float lat,
            Float lon,
            Boolean babyBed,
            Boolean babyChair,
            Boolean babyMenu,
            Boolean babyTableware,
            Boolean stroller,
            Boolean diaperChange,
            Boolean meetingRoom,
            Boolean nursingRoom,
            Boolean parking) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
        this.reviewTotal = reviewTotal;
        this.bookmark = bookmark;
        this.babyBed = babyBed;
        this.babyChair = babyChair;
        this.babyMenu = babyMenu;
        this.babyTableware = babyTableware;
        this.stroller = stroller;
        this.diaperChange = diaperChange;
        this.meetingRoom = meetingRoom;
        this.nursingRoom = nursingRoom;
        this.parking = parking;
    }

    public ResPlaceRestaurantDto(Long id, String name, String address, String phone, Float lat, Float lon) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
    }



}
