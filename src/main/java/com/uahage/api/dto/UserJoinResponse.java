package com.uahage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinResponse {
    private String accessToken;
    private String refreshToken;

    @Builder
    public UserJoinResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
