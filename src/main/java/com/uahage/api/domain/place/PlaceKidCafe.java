package com.uahage.api.domain.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_kid_cafes")
@Getter
@NoArgsConstructor
@ToString
public class PlaceKidCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
    private Float lon;

    @Column(name = "admission_fee", length = 100)
    private String admissionFee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public PlaceKidCafe(String name, String address, String phone, Float lat, Float lon, String admissionFee) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
        this.admissionFee = admissionFee;
    }
}
