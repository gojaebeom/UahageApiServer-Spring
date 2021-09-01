package kr.co.hohocompany.dto;

import kr.co.hohocompany.domain.user.User;
import kr.co.hohocompany.domain.user.UserDetail;
import kr.co.hohocompany.domain.user.UserImage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResShowUserDto {

    private String nickname;
    private String imagePath;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;

    public static ResShowUserDto of(User user, UserDetail userDetail, List<UserImage> userImages){

        return ResShowUserDto.builder()
                .nickname(user.getNickname())
                .ageGroupType(userDetail.getAgeGroupType())
                .babyGender(userDetail.getBabyGender())
                .babyBirthday(userDetail.getBabyBirthday())
                .imagePath(userImages.size() != 0 ? userImages.get(0).getImagePath() : null )
                .build();
    }

    @Builder
    public ResShowUserDto(String nickname, String imagePath, Short ageGroupType, Character babyGender, String babyBirthday) {
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.ageGroupType = ageGroupType;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }
}
