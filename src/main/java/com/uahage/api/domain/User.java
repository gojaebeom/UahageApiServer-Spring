package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String nickname;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserDetail userDetail;
    private UserImage userImage;

    @Builder
    public User(Long id, String email, String nickname, String token) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.token = token;
    }
}
