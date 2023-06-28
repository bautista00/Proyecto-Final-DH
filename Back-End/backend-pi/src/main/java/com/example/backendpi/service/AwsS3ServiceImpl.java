package com.example.backendpi.service;

import com.amazonaws.services.codecommit.model.File;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.net.URL;


@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsS3ServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;


    @Override
    public List<String> uploadFiles(List<MultipartFile> files) throws Exception {
        List<String> uploadedFiles = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String newFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, file.getInputStream(), null);
                amazonS3.putObject(request);
                LOGGER.info("Se sube archivo con el nombre... " + newFileName);
                uploadedFiles.add(newFileName);
            }
            return uploadedFiles;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new Exception("No se subio el archivo correctamente");
    }



    @Override
    public List<String> getObjectsFromS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        return objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    @Override
    public InputStream downloadFile(String key) {
        S3Object object = amazonS3.getObject(bucketName, key);
        return object.getObjectContent();
    }

//    public List<String> generateImageUrls(List<String> fileNames) {
//        List<String> imageUrls = new ArrayList<>();
//        for (String fileName : fileNames) {
//            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
//            Date expiration = new Date();
//            long expirationMillis = expiration.getTime() + (10L * 365 * 24 * 60 * 60 * 1000);
//            expiration.setTime(expirationMillis);
//            generatePresignedUrlRequest.setExpiration(expiration);
//
//            ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides()
//                    .withCacheControl("No-cache")
//                    .withContentDisposition("attachment; filename=" + fileName);
//            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
//
//            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
//            imageUrls.add(url.toString());
//        }
//        return imageUrls;
//    }

        public List<String> generateImageUrls(List<String> fileNames) {
        List<String> imageUrls = new ArrayList<>();
        for (String fileName : fileNames) {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            Date expiration = new Date();
            long expirationMillis = expiration.getTime() + (7L * 24 * 60 * 60 * 1000);
            expiration.setTime(expirationMillis);
            generatePresignedUrlRequest.setExpiration(expiration);

            ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides()
                    .withCacheControl("No-cache")
                    .withContentDisposition("attachment; filename=" + fileName);
            generatePresignedUrlRequest.setResponseHeaders(responseHeaders);

            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
            imageUrls.add(url.toString());
        }
        return imageUrls;
    }






}
