package com.uahage.api.domain.place;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_review_images")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaceRestaurantReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private PlaceRestaurantReview review;

    @Column(name = "image_path", length = 100, nullable = false)
    private String imagePath;

    @Column(name = "preview_image_path", length = 100)
    private String previewImagePath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public PlaceRestaurantReviewImage(Long id, PlaceRestaurantReview review, String imagePath, String previewImagePath) {
        this.id = id;
        this.review = review;
        this.imagePath = imagePath;
        this.previewImagePath = previewImagePath;
    }
}
