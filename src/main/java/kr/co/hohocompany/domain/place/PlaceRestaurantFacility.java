package kr.co.hohocompany.domain.place;

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
    private Boolean babyMenu;

    @Column(name = "baby_bed")
    private Boolean babyBed;

    @Column(name = "baby_tableware")
    private Boolean babyTableware;

    @Column(name = "baby_chair")
    private Boolean babyChair;

    @Column(name = "diaper_change")
    private Boolean diaperChange;

    @Column(name = "stroller")
    private Boolean stroller;

    @Column(name = "meeting_room")
    private Boolean meetingRoom;

    @Column(name = "nursing_room")
    private Boolean nursingRoom;

    @Column(name = "parking")
    private Boolean parking;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
