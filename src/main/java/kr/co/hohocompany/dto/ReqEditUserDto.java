package kr.co.hohocompany.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class ReqEditUserDto {
    private Long id;
    private List<MultipartFile> images;
    private Character imageInit = 'Y';
    private String nickname;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;

    @Builder
    public ReqEditUserDto(Long id, List<MultipartFile> images, Character imageInit, String nickname, Short ageGroupType, Character babyGender, String babyBirthday) {
        this.id = id;
        this.images = images;
        this.imageInit = imageInit;
        this.nickname = nickname;
        this.ageGroupType = ageGroupType;
        this.babyGender = babyGender;
        this.babyBirthday = babyBirthday;
    }
}
