package com.uahage.api.dto;

import com.uahage.api.domain.User;
import com.uahage.api.domain.UserDetail;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ReqEditUserDto {
    private Long id;
    private List<MultipartFile> images;
    private Character imageInit;
    private String nickname;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;

    public User toUser() {
        return User.builder()
                .id(this.id)
                .nickname(this.nickname)
                .build();
    }

    public UserDetail toUserDetail(User user){
        return UserDetail.builder()
                .user(user)
                .ageGroupType(this.ageGroupType)
                .babyGender(this.babyGender)
                .babyBirthday(this.babyBirthday)
                .build();
    }
}
