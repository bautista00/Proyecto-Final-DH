package com.example.backendpi.controller;

import com.example.backendpi.service.AwsS3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class UploadFileController {


    @Autowired
    private AwsS3Service awss3Service;



    @PostMapping("/all/uploadfile")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file) {
        String newFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        awss3Service.uploadFile(file, newFileName);
        String response = "El archivo " + file.getOriginalFilename() + " fue subido exitosamente al bucket S3";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



//    @GetMapping("/getallfiles")
//    public ResponseEntity<List<String>> listFiles() {
//        return new ResponseEntity<List<String>>(awss3Service.getObjectsFromS3(), HttpStatus.OK);
//    }

//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(@RequestParam("key") String key) {
//        InputStreamResource resource  = new InputStreamResource(awss3Service.downloadFile(key));
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+key+"\"").body(resource);
//    }

}
