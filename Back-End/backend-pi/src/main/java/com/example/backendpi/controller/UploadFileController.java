package com.example.backendpi.controller;

import com.example.backendpi.service.api.AwsS3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.util.List;

@RestController
@RequestMapping("/s3")
public class UploadFileController {


    @Autowired
    private AwsS3Service awss3Service;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file) {
        awss3Service.uploadFile(file);
        String response = "El archivo "+file.getOriginalFilename()+" fue subido exitosamente al bucket S3";
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> listFiles() {
        return new ResponseEntity<List<String>>(awss3Service.getObjectsFromS3(), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@RequestParam("key") String key) {
        InputStreamResource resource  = new InputStreamResource(awss3Service.downloadFile(key));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+key+"\"").body(resource);
    }

}
