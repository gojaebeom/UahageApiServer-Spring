package com.uahage.api.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class S3Service {

    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public List<HashMap<String, String>> uploads(String DIR, Long userId, List<MultipartFile> files, Boolean addPreview) throws Exception {
        // TODO: 이미지 파일 없을 시 바로 리턴
        if(files.isEmpty()) return null;

        List<HashMap<String, String>> fileNames = new ArrayList<>();

        for(MultipartFile file : files) {
            verifyFile(file);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            String stringDate = sdf.format(date);
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String stringUserId = String.valueOf(userId);
            String originName = DIR+"/"+"u"+stringUserId+"_"+stringDate+ext;

            String preview = null;
            if(addPreview){
                String name = DIR+"/"+"u"+stringUserId+"_"+stringDate+"_preview"+ext;
                preview = uploadThumbnail(name, file);
            }

            s3Client.putObject(new PutObjectRequest(bucket, originName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            HashMap<String, String> imageNames = new HashMap<>();
            imageNames.put("origin", s3Client.getUrl(bucket, originName).toString());
            imageNames.put("preview", preview);
            fileNames.add(imageNames);
        }
        return fileNames;
    }

    private void verifyFile(MultipartFile file) {
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

    private String uploadThumbnail(String previewName, MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        BufferedImage originalImage = ImageIO.read(in);

//        if(originalImage.getWidth() < 200 && originalImage.getHeight() < 200){
//            log.info("[ 원본 파일 크기 작음 -> 리사이징 안함 ]");
//            return null;
//        }

        int imgWidth = Math.min(originalImage.getHeight(),  originalImage.getWidth() );
        int imgHeight = imgWidth;

        BufferedImage scaledImage = Scalr.crop(originalImage, (originalImage.getWidth() - imgWidth)/2, (originalImage.getHeight() - imgHeight)/2, imgWidth, imgHeight, null);
        BufferedImage thumbnail = Scalr.resize(scaledImage, 200, 200, null);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "png", outStream);
        byte[] buffer = outStream.toByteArray();
        InputStream is = new ByteArrayInputStream(buffer);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/png");
        meta.setContentLength(buffer.length);

        s3Client.putObject(new PutObjectRequest(bucket, previewName, is, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, previewName).toString();
    }

    public void delete(String key) throws Exception {
        //Delete 객체 생성
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, key);
        //Delete
        try{
            this.s3Client.deleteObject(deleteObjectRequest);
            System.out.println(String.format("[%s] deletion complete", key));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
