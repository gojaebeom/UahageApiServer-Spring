package kr.co.hohocompany.dto;

import kr.co.hohocompany.domain.user.User;
import kr.co.hohocompany.domain.user.UserDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqJoinDto {
    private String email;
    private String nickname;
    private Short ageGroupType;
    private String babyGender;
    private String babyBirthday;

    @Builder
    public ReqJoinDto(String email, String nickname, Short ageGroupType, String babyGender, String babyBirthday) {
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
                .babyGender(this.babyGender)
                .babyBirthday(this.babyBirthday)
                .build();
    }
}
