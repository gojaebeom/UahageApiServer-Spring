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
@Table(name = "user_babies")
@Getter
@ToString
@NoArgsConstructor
public class UserBaby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "baby_gender")
    private Character babyGender;

    @Column(name = "baby_birthday")
    private String babyBirthday;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public UserBaby(Long id, User user, Character babyGender, String babyBirthday) {
        this.id = id;
        this.user = user;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }
}
