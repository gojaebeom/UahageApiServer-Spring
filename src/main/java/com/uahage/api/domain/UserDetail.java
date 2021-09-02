package com.uahage.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    private Long id;
    private User user;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserDetail(Long id, User user, Short ageGroupType, Character babyGender, String babyBirthday) {
        this.id = id;
        this.user = user;
        this.ageGroupType = ageGroupType;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }
}
