package com.uahage.api.dto;

import com.uahage.api.domain.place.PlaceInfoPropose;
import com.uahage.api.domain.place.PlaceInfoProposeImage;
import com.uahage.api.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Slf4j
@Getter
public class PlaceInfoProposeStoreRequest {

    private List<MultipartFile> images;
    private Long userId;
    private Long placeCategoryId;
    private Long placeId;
    private String desc;

    public PlaceInfoPropose toInfoPropose(User user) {
        return PlaceInfoPropose.builder()
            .user(user)
            .placeCategoryId(this.placeCategoryId)
            .placeId(this.placeId)
            .description(this.desc)
            .build();
    }

    public PlaceInfoProposeImage toInfoProposeImage(PlaceInfoPropose infoPropose, String origin) {
        return PlaceInfoProposeImage.builder()
                .propose(infoPropose)
                .imagePath(origin)
                .build();
    }
}
