package com.uahage.api.domain.place;

import com.uahage.api.domain.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_reviews")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaceRestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private PlaceRestaurant restaurant;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "total_rating")
    private Float totalRating;

    @Column(name = "taste_rating")
    private Float tasteRating;

    @Column(name = "cost_rating")
    private Float costRating;

    @Column(name = "service_rating")
    private Float serviceRating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public PlaceRestaurantReview(Long id, User user, PlaceRestaurant restaurant, String description, Float totalRating, Float tasteRating, Float costRating, Float serviceRating) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.description = description;
        this.totalRating = totalRating;
        this.tasteRating = tasteRating;
        this.costRating = costRating;
        this.serviceRating = serviceRating;
    }
}
