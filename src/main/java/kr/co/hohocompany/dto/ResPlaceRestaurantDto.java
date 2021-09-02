package kr.co.hohocompany.dto;

import kr.co.hohocompany.domain.place.PlaceRestaurant;
import kr.co.hohocompany.domain.place.PlaceRestaurantFacility;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResPlaceRestaurantDto {
    private Integer id;
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
}
