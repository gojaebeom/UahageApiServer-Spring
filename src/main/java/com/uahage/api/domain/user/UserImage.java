package com.uahage.api.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_images")
@Getter
@ToString
@NoArgsConstructor
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "image_path", length = 100, nullable = false)
    private String imagePath;

    @Column(name = "preview_image_path", length = 100)
    private String previewImagePath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public UserImage(Long id, User user, String imagePath, String previewImagePath) {
        this.id = id;
        this.user = user;
        this.imagePath = imagePath;
        this.previewImagePath = previewImagePath;
    }
}
