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
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
        // TODO: ????????? ?????? ?????? ??? ?????? ??????
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



            File _file = convert(file);
            int orientation = 1; // ????????????, 1. 0???, 3. 180???, 6. 270???, 8. 90??? ????????? ??????

            Metadata metadata; // ????????? ?????? ????????? ??????
            Directory directory; // ???????????? Exif ???????????? ?????? ?????? ??????
            JpegDirectory jpegDirectory; // JPG ????????? ????????? ?????? ?????? ??????

            try{
                metadata = JpegMetadataReader.readMetadata(_file);
                directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
                if(directory != null){
                    orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION); // ????????????
                }
            }catch (Exception e){
                orientation=1;
            }
            //imageFile
            BufferedImage srcImg = ImageIO.read(_file);
            // ?????? ?????????.
            System.out.println(orientation);
            switch (orientation) {
                case 6:
                    srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_90, null);
                    break;
                case 1:
                    break;
                case 3:
                    srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_180, null);
                    break;
                case 8:
                    srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_270, null);
                    break;
                default:
                    orientation = 1;
                    break;
            }

            String preview = null;
            if(addPreview){
                String name = DIR+"/"+"u"+stringUserId+"_"+stringDate+"_preview"+ext;
                preview = uploadThumbnail(name, srcImg);
            }

            s3Client.putObject(new PutObjectRequest(bucket, originName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            System.out.println(ext);

            HashMap<String, String> imageNames = new HashMap<>();
            imageNames.put("origin", s3Client.getUrl(bucket, originName).toString());
            imageNames.put("preview", preview);
            fileNames.add(imageNames);

            _file.delete();
        }
        return fileNames;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file= new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    private void verifyFile(MultipartFile file) {
        String fileName = file.getOriginalFilename().replaceAll("\\s+","");
        log.info("[ ????????? ????????? ]");
        log.info(fileName);
        long fileSize = file.getSize();

        String regExp = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
        System.out.println("?????? ?????? : "+ fileName);
        // TODO: ????????? ?????? ?????? ??????
        if(!fileName.matches(regExp)){
            throw new IllegalArgumentException("Is Not Image File: jpg, png, gif, bmp ????????? ????????? ????????? ??? ????????????.");
        }
        log.info("[ ????????? ?????? OK ]");

        System.out.println("?????? ????????? : "+ fileSize);
        final int limitSize = 2000000;
        // TODO: ????????? ????????? ????????? ?????? ??????
        if( fileSize > limitSize  ){
            throw new IllegalArgumentException("File Size Overflow: ?????? ????????? ???????????? ?????? 2MB??? ???????????????.");
        }
        log.info("[ ?????? ????????? ?????? OK ]");
    }

    private String uploadThumbnail(String previewName, BufferedImage originalImage) throws IOException {
        int imgWidth = Math.min(originalImage.getHeight(), originalImage.getWidth());
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
        //Delete ?????? ??????
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
