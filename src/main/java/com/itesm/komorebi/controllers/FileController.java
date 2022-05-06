package com.itesm.komorebi.controllers;

import com.itesm.komorebi.util.S3Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    S3Util s3Util;

    @Operation(summary = "Add a file to the DB", description = "The method receive a file that will be uploaded to the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = String.class),
                    mediaType = "application/json"
            )})
    })
    @PostMapping("/file-upload")
    public String fileUpload(
            @Parameter(description = "The file to be uploaded", required = true) @RequestParam("file") MultipartFile file){
        try {
            s3Util.upload("amazon-connect-s3-calls","test.pdf",file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploaded";
    }
}