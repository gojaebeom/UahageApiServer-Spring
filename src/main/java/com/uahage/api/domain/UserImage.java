package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImage {
    private Long id;
    private String imagePath;
    private String previewImagePath;
    @JsonIgnore
    private User user;

    @Builder
    public UserImage(Long id, User user, String imagePath, String previewImagePath) {
        this.id = id;
        this.user = user;
        this.imagePath = imagePath;
        this.previewImagePath = previewImagePath;
    }
}
