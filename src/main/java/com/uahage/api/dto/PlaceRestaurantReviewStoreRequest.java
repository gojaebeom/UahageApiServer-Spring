package com.uahage.api.dto;

import com.uahage.api.domain.place.PlaceRestaurant;
import com.uahage.api.domain.place.PlaceRestaurantReview;
import com.uahage.api.domain.user.User;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.List;

@Setter
@ToString
@Slf4j
public class PlaceRestaurantReviewStoreRequest {
    private Long reviewId;
    private Long placeId;
    private Long userId;
    private List<MultipartFile> images;
    private String desc;
    private Float tasteRating;
    private Float costRating;
    private Float serviceRating;
    private Float totalRating;
    private List<Long> deleteImgIds;

    public Long getReviewIdOrThrowException(){
        log.info("[ reviewId 유효성 검사 : Soon ]");
        if(this.reviewId == null){
            throw new IllegalArgumentException("리뷰 Id가 존재하지 않습니다.");
        }
        log.info("[ reviewId 유효성 검사 : OK ]");
        return this.reviewId;
    }

    public Long getPlaceIdOrThrowException(){
        log.info("[ placeId 유효성 검사 : Soon ]");
        if(this.placeId == null){
            throw new IllegalArgumentException("장소 Id가 존재하지 않습니다.");
        }
        log.info("[ placeId 유효성 검사 : OK ]");
        return this.placeId;
    }

    public Long getUserIdOrThrowException(){
        log.info("[ userId 유효성 검사 : Soon ]");
        if(this.userId == null){
            throw new IllegalArgumentException("회원 Id가 존재하지 않습니다.");
        }
        log.info("[ userId 유효성 검사 : OK ]");
        return this.userId;
    }

    public boolean verifyImages() {
        if(this.images == null){
            return false;
        }
        return true;
    }

    public List<MultipartFile> getImagesOrThrowException(){
        log.info("[ 이미지 유효성 검사 ]");
        if(this.images != null){
            log.info("[ 이미지 파일 존재여부 OK ]");
            for(MultipartFile file : this.images){
                String fileName = file.getOriginalFilename().replaceAll("\\s+","");
                log.info("[ 이미지 파일명 ]");
                log.info(fileName);
                long fileSize = file.getSize();

                String regExp = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
                System.out.println("파일 이름 : "+ fileName);
                // TODO: 이미지 파일 타입 검사
                if(!fileName.matches(regExp)){
                    throw new IllegalArgumentException("Is Not Image File: jpg, png, gif, bmp 확장자 파일만 사용할 수 있습니다.");
                }
                log.info("[ 확장자 검사 OK ]");

                System.out.println("파일 사이즈 : "+ fileSize);
                final int limitSize = 2000000;
                // TODO: 이미지 사이즈 초과시 실패 응답
                if( fileSize > limitSize  ){
                    throw new IllegalArgumentException("File Size Overflow: 파일 하나의 사이즈는 최대 2MB로 제한됩니다.");
                }
                log.info("[ 파일 사이즈 검사 OK ]");
            }
        }
        return this.images;
    }

    public Boolean verifyDesc(){
        if(this.desc.equals("") || this.desc == null){
            return false;
        }
        return true;
    }

    public String getDescOrThrowException(){
        log.info("[ 내용 유효성 검사 : Soon ]");
        if(this.desc.equals("") || this.desc == null){
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        log.info("[ 내용 유효성 검사 : OK ]");
        return this.desc;
    }

    public Float getTasteRatingOrThrowException(){
        log.info("[ 맛 평점 유효성 검사 : Soon ]");
        if(this.tasteRating == null){
            throw new IllegalArgumentException("맛에 대한 평점을 입력해주세요.");
        }
        log.info("[ 맛 평점 유효성 검사 : OK ]");
        return this.tasteRating;
    }


    public boolean verifyTasteRating() {
        if(this.tasteRating == null)
            return false;
        return true;
    }

    public Float getCostRatingOrThrowException(){
        log.info("[ 가격 평점 유효성 검사 : Soon ]");
        if(this.costRating == null){
            throw new IllegalArgumentException("가격에 대한 평점을 입력해주세요.");
        }
        log.info("[ 가격 평점 유효성 검사 : OK ]");
        return this.costRating;
    }
    public boolean verifyCostRating() {
        if(this.costRating == null){
           return false;
        }
        return true;
    }

    public Float getServiceRatingOrThrowException(){
        log.info("[ 서비스 평점 유효성 검사 : Soon ]");
        if(this.serviceRating == null){
            throw new IllegalArgumentException("서비스에 대한 평점을 입력해주세요.");
        }
        log.info("[ 서비스 평점 유효성 검사 : OK ]");
        return this.serviceRating;
    }

    public boolean verifyServiceRating() {
        if(this.serviceRating == null){
            return false;
        }
        return true;
    }

    public Float getTotalRatingOrThrowException(){
        Float total = (this.tasteRating + this.costRating + this.serviceRating)/3;
        System.out.println("토탈레이팅 검사");
        System.out.println(total);
        DecimalFormat form = new DecimalFormat("#.#");
        this.totalRating = Float.parseFloat(form.format(total));

        log.info("[ 총 평점 유효성 검사 : OK ]");
        return this.totalRating;
    }

    public boolean verifyTotalRating() {
        if (this.totalRating == null) {
            return false;
        }
        return true;
    }

    public boolean verifyDeleteImgIds(){
        if(this.deleteImgIds == null){
            return false;
        }
        return true;
    }
    public List<Long> getDeleteImgIdsOrThrowException(){
        if(this.deleteImgIds == null){
            throw new IllegalArgumentException("삭제할 이미지 리스트가 없습니다.");
        }
        return this.deleteImgIds;
    }

    public PlaceRestaurantReview toRestaurantReview(PlaceRestaurant restaurant, User user) {
        return PlaceRestaurantReview.builder()
                .user(user)
                .restaurant(restaurant)
                .description(this.getDescOrThrowException())
                .tasteRating(this.getTasteRatingOrThrowException())
                .costRating(this.getCostRatingOrThrowException())
                .serviceRating(this.getServiceRatingOrThrowException())
                .totalRating(this.getTotalRatingOrThrowException())
                .build();
    }


}
