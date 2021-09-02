package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollow {

    private Long id;
    private User follower;
    private User following;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
