package kr.co.hohocompany.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResJoinDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public ResJoinDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
