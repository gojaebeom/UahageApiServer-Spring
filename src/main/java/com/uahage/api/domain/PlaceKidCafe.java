package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceKidCafe {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float lat;
    private Float lon;
    private String admissionFee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
