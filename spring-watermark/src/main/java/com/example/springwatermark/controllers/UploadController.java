package com.example.springwatermark.controllers;

import com.example.springwatermark.dtos.ReqImageBase64DTO;
import com.example.springwatermark.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image-file")
    public void uploadImageFile(@RequestParam MultipartFile imageFile) {
        uploadService.uploadImageFile(imageFile);
    }

    @PostMapping("/image-base64")
    public void uploadImageBase64(@RequestBody ReqImageBase64DTO dto) {
        uploadService.uploadImageBase64(dto);
    }
}
