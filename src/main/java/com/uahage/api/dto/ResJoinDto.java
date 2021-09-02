package com.uahage.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ResJoinDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public ResJoinDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
