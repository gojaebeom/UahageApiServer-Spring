package com.uahage.api.domain.place;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_facilities")
@Setter
@Getter
@ToString
public class PlaceRestaurantFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private PlaceRestaurant restaurant;

    @Column(name = "baby_menu")
    private boolean babyMenu;

    @Column(name = "baby_bed")
    private boolean babyBed;

    @Column(name = "baby_tableware")
    private boolean babyTableware;

    @Column(name = "baby_chair")
    private boolean babyChair;

    @Column(name = "diaper_change")
    private boolean diaperChange;

    @Column(name = "stroller")
    private boolean stroller;

    @Column(name = "meeting_room")
    private boolean meetingRoom;

    @Column(name = "nursing_room")
    private boolean nursingRoom;

    @Column(name = "parking")
    private boolean parking;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
