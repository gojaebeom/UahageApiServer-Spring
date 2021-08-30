package kr.co.hohocompany.domain.user;

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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "origin_path", length = 100, nullable = false)
    private String originPath;

    @Column(name = "preview_path", length = 100)
    private String previewPath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public UserImage(Long id, User user, String originPath, String previewPath) {
        this.id = id;
        this.user = user;
        this.originPath = originPath;
        this.previewPath = previewPath;
    }
}
