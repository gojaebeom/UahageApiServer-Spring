package com.uahage.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResShowUserDto {

    private Long id;
    private String nickname;
    private String imagePath;
    private Short ageGroupType;
    private Character babyGender;
    private String babyBirthday;
}
