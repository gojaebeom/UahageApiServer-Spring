package com.uahage.api.service;

import com.uahage.api.domain.place.PlaceRestaurant;
import com.uahage.api.domain.place.PlaceRestaurantReview;
import com.uahage.api.domain.place.PlaceRestaurantReviewImage;
import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserImage;
import com.uahage.api.dto.*;
import com.uahage.api.mapper.PlaceRestaurantReviewMapper;
import com.uahage.api.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<PlaceRestaurantReviewsResponse> index(PlaceRestaurantReviewsRequest restaurantReviewsRequest){

        return restaurantReviewMapper.findAllByReviewIdWithOrderByOption(restaurantReviewsRequest);
    }

    public List<PlaceRestaurantReviewImagesResponse> indexOnlyImage(PlaceRestaurantReviewsRequest restaurantReviewsRequest) {
        return restaurantReviewMapper.findImagesByRestaurantId(restaurantReviewsRequest.getPlaceId());
    }

    public HashMap shared(List<PlaceRestaurantReviewsResponse> restaurantReviewsResponses){
        Float total = 0f;
        int fivePointTotal = 0;
        int fourPointTotal = 0;
        int threePointTotal = 0;
        int twoPointTotal = 0;
        int onePointTotal = 0;
        for (PlaceRestaurantReviewsResponse restaurantReviewsResponse : restaurantReviewsResponses){
            Float myTotalRating = restaurantReviewsResponse.getTotalRating();
            total += myTotalRating;

            // 5????????? ?????? ????????????
            if (myTotalRating == 5) {
                fivePointTotal += 1;
            } else if (4 <= myTotalRating && myTotalRating < 5) {
                fourPointTotal += 1;
            } else if (3 <= myTotalRating && myTotalRating < 4) {
                threePointTotal += 1;
            } else if (2 <= myTotalRating && myTotalRating < 3) {
                twoPointTotal += 1;
            } else if (1 <= myTotalRating && myTotalRating < 2) {
                onePointTotal += 1;
            }
        }
        Float tr = total/restaurantReviewsResponses.size();

        if(tr.isNaN()){
            tr = 0.0F;
        }else{
            DecimalFormat form = new DecimalFormat("#.#");
            tr = Float.parseFloat(form.format(tr));
        }

        HashMap<String, Object> shared = new HashMap<>();
        shared.put("total", restaurantReviewsResponses.size());
        shared.put("allTotalRating", tr);
        shared.put("fivePointTotal", fivePointTotal);
        shared.put("fourPointTotal", fourPointTotal);
        shared.put("threePointTotal", threePointTotal);
        shared.put("twoPointTotal", twoPointTotal);
        shared.put("onePointTotal", onePointTotal);

        return shared;
    }

    public PlaceRestaurantReviewsResponse show(Long reviewId){
        return restaurantReviewMapper.findOneById(reviewId);
    }

    public void store(PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest) throws Exception {
        PlaceRestaurant restaurant = restaurantRepository.findOneById(restaurantReviewStoreRequest.getPlaceIdOrThrowException());
        System.out.println(restaurant);
        User user = userRepository.findOneById(restaurantReviewStoreRequest.getUserIdOrThrowException());
        System.out.println(user);

        log.info("???????????? ?????? ?????? : Soon ");
        PlaceRestaurantReview restaurantReview = restaurantReviewStoreRequest.toRestaurantReview(restaurant, user);
        System.out.println(restaurantReview);
        restaurantReviewRepository.save(restaurantReview);
        log.info("???????????? ?????? ?????? : OK ");

        if(restaurantReviewStoreRequest.verifyImages()) {
            List<PlaceRestaurantReviewImage> restaurantReviewImages = new ArrayList<>();
            log.info("[ ????????? ????????? ?????? -> s3 ????????? ????????? ?????????, DB??? ????????? ?????? ?????? ]");
            List<HashMap<String, String>> fileNames = s3Service.uploads("restaurant_reviews", user.getId(), restaurantReviewStoreRequest.getImagesOrThrowException(), true);

            for (HashMap<String, String> fileName: fileNames) {
                PlaceRestaurantReviewImage reviewImage = PlaceRestaurantReviewImage
                        .builder()
                        .review(restaurantReview)
                        .imagePath(fileName.get("origin"))
                        .previewImagePath(fileName.get("preview"))
                        .build();
                restaurantReviewImages.add(reviewImage);
            }
            System.out.println(restaurantReviewImages);
            restaurantReviewImageRepository.saveAll(restaurantReviewImages);
            log.info("???????????? ?????? ????????? ?????? : OK ");
        }
    }

    public void edit(PlaceRestaurantReviewStoreRequest restaurantReviewStoreRequest) throws Exception {
        log.info("?????? ??????");
        PlaceRestaurantReview restaurantReview = restaurantReviewRepository.findOneById(restaurantReviewStoreRequest.getReviewIdOrThrowException());

        if(restaurantReview == null){
            throw new IllegalArgumentException("????????? ???????????? ????????????");
        }
        log.info("?????? ??????");
        if(restaurantReviewStoreRequest.verifyDesc()){
            restaurantReview.setDescription(restaurantReviewStoreRequest.getDescOrThrowException());
        }
        log.info("?????? OK");
        if(restaurantReviewStoreRequest.verifyTasteRating()){
            restaurantReview.setTasteRating(restaurantReviewStoreRequest.getTasteRatingOrThrowException());
        }
        log.info("??? ?????? OK");
        if(restaurantReviewStoreRequest.verifyCostRating()){
            restaurantReview.setCostRating(restaurantReviewStoreRequest.getCostRatingOrThrowException());
        }
        log.info("?????? ?????? OK");
        if(restaurantReviewStoreRequest.verifyServiceRating()){
            restaurantReview.setServiceRating(restaurantReviewStoreRequest.getServiceRatingOrThrowException());
        }
        log.info("????????? ?????? OK");
        restaurantReview.setTotalRating(restaurantReviewStoreRequest.getTotalRatingOrThrowException());

        log.info("?????? ?????? OK");
        if(restaurantReviewStoreRequest.verifyImages()) {
            List<PlaceRestaurantReviewImage> restaurantReviewImages = new ArrayList<>();
            log.info("[ ????????? ????????? ?????? -> s3 ????????? ????????? ?????????, DB??? ????????? ?????? ?????? ]");
            List<HashMap<String, String>> fileNames = s3Service.uploads("restaurant_reviews", restaurantReview.getUser().getId(), restaurantReviewStoreRequest.getImagesOrThrowException(), true);

            for (HashMap<String, String> fileName: fileNames) {
                PlaceRestaurantReviewImage reviewImage = PlaceRestaurantReviewImage
                        .builder()
                        .review(restaurantReview)
                        .imagePath(fileName.get("origin"))
                        .previewImagePath(fileName.get("preview"))
                        .build();
                restaurantReviewImages.add(reviewImage);
            }
            System.out.println(restaurantReviewImages);
            restaurantReviewImageRepository.saveAll(restaurantReviewImages);
            log.info("???????????? ?????? ????????? ?????? : OK ");
        }

        if(restaurantReviewStoreRequest.verifyDeleteImgIds()){
            for(Long id : restaurantReviewStoreRequest.getDeleteImgIdsOrThrowException()){
                PlaceRestaurantReviewImage reviewImage = restaurantReviewImageRepository.findOneById(id);
                if(reviewImage != null){
                    String[] imagePaths = reviewImage.getImagePath().split("/");
                    String[] previewImagePaths = reviewImage.getPreviewImagePath().split("/");

                    s3Service.delete("restaurant_reviews/".concat(imagePaths[imagePaths.length-1]));
                    s3Service.delete("restaurant_reviews/".concat(previewImagePaths[previewImagePaths.length-1]));

                    restaurantReviewImageRepository.deleteById(id);
                }
                log.info("????????? ????????? ??????");
            }
        }

    }

    public void destroy(Long reviewId) throws Exception {
        destroyImage(reviewId);
        log.info("[ ?????? ?????? ?????? ]");
        PlaceRestaurantReview restaurantReview = restaurantReviewRepository.findOneById(reviewId);
        if(restaurantReview == null) throw new IllegalArgumentException("???????????? ????????? ????????????.");
        restaurantReviewRepository.delete(restaurantReview);
    }

    private void destroyImage(Long reviewId) throws Exception {
        log.info("[ ????????? ????????? ?????? -> s3 ???????????? ????????? ?????? ]");
        List<PlaceRestaurantReviewImage> restaurantReviewImages = restaurantReviewImageRepository.findAllByReviewId(reviewId);
        System.out.println(restaurantReviewImages);
        for(PlaceRestaurantReviewImage restaurantReviewImage : restaurantReviewImages){
            String[] imagePaths = restaurantReviewImage.getImagePath().split("/");
            String[] previewImagePaths = restaurantReviewImage.getPreviewImagePath().split("/");

            s3Service.delete("restaurant_reviews/".concat(imagePaths[imagePaths.length-1]));
            s3Service.delete("restaurant_reviews/".concat(previewImagePaths[previewImagePaths.length-1]));

            restaurantReviewImageRepository.deleteById(restaurantReviewImage.getId());
        }
    }


    public Long findUserIdByReviewId(Long reviewId) {
        PlaceRestaurantReview review = restaurantReviewRepository.findOneById(reviewId);
        return review.getUser().getId();
    }
}
