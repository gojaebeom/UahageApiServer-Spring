package kr.co.hohocompany.domain.place;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_review_images")
@Getter
@Setter
@ToString
public class PlaceRestaurantReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private PlaceRestaurant restaurant;

    @Column(name = "origin_path", length = 100, nullable = false)
    private String originPath;

    @Column(name = "preview_path", length = 100)
    private String previewPath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
