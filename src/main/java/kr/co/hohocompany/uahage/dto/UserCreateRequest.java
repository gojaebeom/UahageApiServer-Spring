package kr.co.hohocompany.uahage.dto;

import kr.co.hohocompany.uahage.domain.user.User;
import kr.co.hohocompany.uahage.domain.user.UserDetail;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@ToString
public class UserCreateRequest {

    private String email;
    private String nickname;
    private short ageGroupType;
    private char babyGender;
    private String babyBirthday;

    public User toUserEntity() {
        return User.builder()
                .email(email)
                .nickname(this.nickname)
                .build();
    }

    public UserDetail toUserDetailEntity(User user) {
        return UserDetail.builder()
                .ageGroupType(this.ageGroupType)
                .babyBirthday(this.babyBirthday)
                .babyGender(this.babyGender)
                .user(user)
                .build();
    }

//    public List<UserImage> toUserImageEntities(List<String> imgPathStrings) {
//        List<UserImage> userImages;
//        for(String imgPathString : imgPathStrings ){
//
//        }
//    }

}
