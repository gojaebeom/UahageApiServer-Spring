package com.uahage.api.dto;

import lombok.*;

@Data
@Builder
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
}
