package com.example.backendpi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AwsS3Service {

    List<String> uploadFiles(List<MultipartFile> files) throws Exception;

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);

    List<String> generateImageUrls(List<String> fileName);
}
