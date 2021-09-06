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
        if(this.email == null)
            throw new IllegalArgumentException("회원 이메일이 입력되지 않았습니다.");

        if(this.nickname == null)
            throw new IllegalArgumentException("회원 닉네임이 입력되지 않았습니다.");

        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .build();
    }

    public UserDetail toUserDetail(User user) {
        if(this.ageGroupType == null)
            this.ageGroupType = 6;

        if(this.babyGender == null)
            this.babyGender = 'M';

        return UserDetail.builder()
                .user(user)
                .ageGroupType(this.ageGroupType)
                .babyBirthday(this.babyBirthday)
                .babyGender(this.babyGender)
                .build();
    }
}
