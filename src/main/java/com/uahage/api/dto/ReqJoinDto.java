package com.uahage.api.dto;

import com.uahage.api.domain.User;
import com.uahage.api.domain.UserDetail;
import lombok.*;

@Data
@NoArgsConstructor
public class ReqJoinDto {
    private String email;
    private String nickname;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;

    @Builder
    public ReqJoinDto(String email, String nickname, Short ageGroupType, Character babyGender, String babyBirthday) {
        this.email = email;
        this.nickname = nickname;
        this.ageGroupType = ageGroupType;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }

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
