package com.uahage.api.dto;

import com.uahage.api.domain.User;
import com.uahage.api.domain.UserDetail;
import com.uahage.api.domain.UserImage;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResShowUserDto {
    private User user;
    private UserDetail detail;
    private UserImage image;
}
