package com.example.demobackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author wh
 * @Date 2025/1/22
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("fileUpload")
    public String fileUpload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return file.getOriginalFilename();
    }
}
