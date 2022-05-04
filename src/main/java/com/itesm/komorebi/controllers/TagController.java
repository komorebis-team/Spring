package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Configuration;
import com.itesm.komorebi.models.Tag;
import com.itesm.komorebi.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    @Autowired
    ConfigurationService configurationService;

    @PostMapping("/add")
    public ResponseEntity<?> addTag(@RequestBody Tag tag){
        Configuration configuration = configurationService.addTag(tag.getCategoryName(), tag.getName());
        if (configuration == null){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configuration, HttpStatus.CREATED);
    }
}
