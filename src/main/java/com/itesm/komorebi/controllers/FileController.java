package com.itesm.komorebi.controllers;

import com.itesm.komorebi.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    S3Util s3Util;

    @PostMapping("/file-upload")
    public String fileUpload(@RequestParam("file") MultipartFile file){
        try {
            s3Util.upload("amazon-connect-s3-calls","test.pdf",file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploaded";
    }
}