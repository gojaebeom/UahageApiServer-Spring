package com.uahage.api.service;

import com.uahage.api.domain.place.PlaceRestaurant;
import com.uahage.api.domain.place.PlaceRestaurantReview;
import com.uahage.api.domain.place.PlaceRestaurantReviewImage;
import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserImage;
import com.uahage.api.dto.PlaceRestaurantReviewStoreRequest;
import com.uahage.api.mapper.PlaceRestaurantReviewMapper;
import com.uahage.api.repository.PlaceRestaurantRepository;
import com.uahage.api.repository.PlaceRestaurantReviewImageRepository;
import com.uahage.api.repository.PlaceRestaurantReviewRepository;
import com.uahage.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PlaceRestaurantReviewService {

    private PlaceRestaurantRepository restaurantRepository;
    private PlaceRestaurantReviewRepository restaurantReviewRepository;
    private PlaceRestaurantReviewImageRepository restaurantReviewImageRepository;
    private UserRepository userRepository;

    private PlaceRestaurantReviewMapper restaurantReviewMapper;

    private S3Service s3Service;

    public void store(PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest) throws Exception {

        PlaceRestaurant restaurant = restaurantRepository.findOneById(restaurantReviewStoreRequest.getPlaceIdOrThrowException());
        System.out.println(restaurant);
        User user = userRepository.findOneById(restaurantReviewStoreRequest.getUserIdOrThrowException());
        System.out.println(user);

        log.info("레스토랑 리뷰 생성 : Soon ");
        PlaceRestaurantReview restaurantReview = restaurantReviewStoreRequest.toRestaurantReview(restaurant, user);
        System.out.println(restaurantReview);
        restaurantReviewRepository.save(restaurantReview);
        log.info("레스토랑 리뷰 생성 : OK ");

        if(restaurantReviewStoreRequest.verifyImages()) {
            List<PlaceRestaurantReviewImage> restaurantReviewImages = new ArrayList<>();
            log.info("[ 이미지 업로드 요청 -> s3 서버에 이미지 업로드, DB에 이미지 이름 등록 ]");
            List<String> fileNames = s3Service.upload("restaurant_reviews", user.getId(), restaurantReviewStoreRequest.getImagesOrThrowException());

            for (String fileName : fileNames) {
                PlaceRestaurantReviewImage reviewImage = PlaceRestaurantReviewImage
                        .builder()
                        .placeRestaurantReview(restaurantReview)
                        .imagePath(fileName)
                        .build();
                restaurantReviewImages.add(reviewImage);
            }
            System.out.println(restaurantReviewImages);
            restaurantReviewImageRepository.saveAll(restaurantReviewImages);
            log.info("레스토랑 리뷰 이미지 생성 : OK ");
        }
    }
}
