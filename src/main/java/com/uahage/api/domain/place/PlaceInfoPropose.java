package com.uahage.api.domain.place;

import com.uahage.api.domain.user.User;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "p_info_propose")
@Entity
@NoArgsConstructor
public class PlaceInfoPropose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place_category_id")
    private Long placeCategoryId;

    @Column(name = "place_id")
    private Long placeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public PlaceInfoPropose(Long id, Long placeCategoryId, Long placeId, User user, String description) {
        this.id = id;
        this.placeCategoryId = placeCategoryId;
        this.placeId = placeId;
        this.user = user;
        this.description = description;
    }
}
