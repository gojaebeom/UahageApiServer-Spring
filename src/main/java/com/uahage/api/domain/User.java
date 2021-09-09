package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String nickname;
    private Short ageGroupType;
    @JsonIgnore
    private String refreshToken;

    @Builder
    public User(Long id, String email, String nickname, Short ageGroupType, String refreshToken) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.ageGroupType = ageGroupType;
        this.refreshToken = refreshToken;
    }
}
