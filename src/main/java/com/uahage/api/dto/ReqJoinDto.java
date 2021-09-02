package com.uahage.api.dto;

import com.uahage.api.domain.User;
import com.uahage.api.domain.UserDetail;
import lombok.*;

@Data
@Builder
public class ReqJoinDto {
    private String email;
    private String nickname;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;

    public User toUser() {
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .build();
    }

    public UserDetail toUserDetail(User user) {
        return UserDetail.builder()
                .user(user)
                .ageGroupType(this.ageGroupType)
                .babyBirthday(this.babyBirthday)
                .babyGender(this.babyGender)
                .build();
    }
}
