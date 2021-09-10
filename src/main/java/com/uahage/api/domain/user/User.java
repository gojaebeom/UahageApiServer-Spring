package com.uahage.api.domain.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(name = "age_group_type")
    private Short ageGroupType;

    @Column(name = "refresh_token")
    private String refreshToken;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public User(Long id, String email, String nickname, Short ageGroupType, String refreshToken) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.ageGroupType = ageGroupType;
        this.refreshToken = refreshToken;
    }
}
