package com.uahage.api.service;

import com.uahage.api.domain.place.PlaceInfoPropose;
import com.uahage.api.domain.place.PlaceInfoProposeImage;
import com.uahage.api.domain.user.User;
import com.uahage.api.dto.PlaceInfoProposeStoreRequest;
import com.uahage.api.repository.PlaceInfoProposeImageRepository;
import com.uahage.api.repository.PlaceInfoProposeRepository;
import com.uahage.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PlaceInfoProposeService {

    private PlaceInfoProposeRepository infoProposeRepository;
    private PlaceInfoProposeImageRepository infoProposeImageRepository;
    private UserRepository userRepository;

    private S3Service s3Service;

    public void store(PlaceInfoProposeStoreRequest infoProposeStoreRequest) throws Exception {
        User user = userRepository.findUserById(infoProposeStoreRequest.getUserId());
        PlaceInfoPropose infoPropose = infoProposeStoreRequest.toInfoPropose(user);
        infoProposeRepository.save(infoPropose);

        if(infoProposeStoreRequest.getImages() != null){
            List<HashMap<String, String>> images = s3Service.uploads("/propose", user.getId(), infoProposeStoreRequest.getImages(), false);
            for(HashMap<String, String> image : images){
                PlaceInfoProposeImage infoProposeImage = infoProposeStoreRequest.toInfoProposeImage(infoPropose, image.get("origin"));
                infoProposeImageRepository.save(infoProposeImage);
            }
        }
    }
}
