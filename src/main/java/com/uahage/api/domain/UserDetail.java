package com.uahage.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetail {
    private Long id;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;
    @JsonIgnore
    private User user;

    @Builder
    public UserDetail(Long id, User user, Short ageGroupType, Character babyGender, String babyBirthday) {
        this.id = id;
        this.user = user;
        this.ageGroupType = ageGroupType;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }
}
