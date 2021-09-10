package com.uahage.api.dto;

import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserBaby;
import com.uahage.api.domain.user.UserImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserShowResponse {

    private User user;

    @ToString
    static class User{
        private Long id;
        private String nickname;
        private Short ageGroupType;
        private List<UserBaby> userBabies;
        private UserImage userImage;
    }

    @ToString
    static class UserBaby{
        private String babyBirthday;
        private Character babyGender;
    }

    @ToString
    static class UserImage{
        private String imagePath;
    }
}
