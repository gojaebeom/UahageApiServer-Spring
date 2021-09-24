package com.uahage.api.domain.place;

import com.uahage.api.domain.user.User;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "p_info_propose_images")
@Entity
@NoArgsConstructor
public class PlaceInfoProposeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "p_info_id")
    private PlaceInfoPropose propose;

    @Column(name = "image_path", length = 200, nullable = false)
    private String imagePath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public PlaceInfoProposeImage(Long id, PlaceInfoPropose propose, String imagePath) {
        this.id = id;
        this.propose = propose;
        this.imagePath = imagePath;
    }
}
