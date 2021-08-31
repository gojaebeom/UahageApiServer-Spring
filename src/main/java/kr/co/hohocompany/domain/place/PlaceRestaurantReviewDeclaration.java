package kr.co.hohocompany.domain.place;

import kr.co.hohocompany.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_review_declarations")
@Getter
@Setter
@ToString
public class PlaceRestaurantReviewDeclaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = PlaceRestaurantReviewDeclarationCategory.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private PlaceRestaurantReviewDeclarationCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private PlaceRestaurantReview review;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
