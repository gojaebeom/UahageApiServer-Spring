package com.uahage.api.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.net.BindException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
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

    public List<String> upload(String DIR, Long userId, List<MultipartFile> files) throws Exception {
        // TODO: 이미지 파일 없을 시 바로 리턴
        if(files.isEmpty()) return null;

        List<String> fileNames = new ArrayList<>();

        for(MultipartFile file : files) {
            // TODO: 이미지 검사
            validationImageFile(file);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            String stringDate = sdf.format(date);
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String stringUserId = String.valueOf(userId);
            String fullImageName = DIR+"/"+"u"+stringUserId+"_"+stringDate+ext;


            s3Client.putObject(new PutObjectRequest(bucket, fullImageName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            fileNames.add(s3Client.getUrl(bucket, fullImageName).toString());
        }
        return fileNames;
    }

    private void validationImageFile(MultipartFile file) throws Exception {

        String fileName = file.getOriginalFilename().replaceAll("\\s+","");;
        long fileSize = file.getSize();

        String regExp = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
        System.out.println("파일 이름 : "+ fileName);
        // TODO: 이미지 파일 타입 검사
        if(!fileName.matches(regExp)){
            throw new BindException("Is Not Image File: jpg, png, gif, bmp 확장자 파일만 사용할 수 있습니다.");
        }

        System.out.println("파일 사이즈 : "+ fileSize);
        final int limitSize = 2000000;
        // TODO: 이미지 사이즈 초과시 실패 응답
        if( fileSize > limitSize  ){
            throw new BindException("File Size Overflow: 파일 하나의 사이즈는 최대 2MB로 제한됩니다.");
        }
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
