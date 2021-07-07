package kr.co.hohocompany.uahage.domain.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_login", columnDefinition = "smallint default 0")
    private short isLogin;

    @Column(name = "last_logged_at", columnDefinition = "timestamp")
    private LocalDateTime lastLoggedAt;

    @Column(name = "last_out_at", columnDefinition = "timestamp")
    private LocalDateTime lastOutAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public User(Long id, String email, String password, String nickname, String refreshToken, short isLogin, LocalDateTime lastLoggedAt, LocalDateTime lastOutAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.refreshToken = refreshToken;
        this.isLogin = isLogin;
        this.lastLoggedAt = lastLoggedAt;
        this.lastOutAt = lastOutAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
