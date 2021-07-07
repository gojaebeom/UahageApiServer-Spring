package kr.co.hohocompany.uahage.domain.place;

import kr.co.hohocompany.uahage.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_restaurant_bookmarks")
@Getter
@Setter
@ToString
public class PlaceRestaurantBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = PlaceRestaurant.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "restaurant_id")
    private PlaceRestaurant restaurant;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
