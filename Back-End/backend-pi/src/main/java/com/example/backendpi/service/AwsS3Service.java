package com.example.backendpi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AwsS3Service {

    String uploadFile(MultipartFile file) throws Exception;

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);

    String generateImageUrl(String fileName);
}
