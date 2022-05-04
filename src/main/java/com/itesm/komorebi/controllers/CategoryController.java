package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.models.Configuration;
import com.itesm.komorebi.services.CategoryService;
import com.itesm.komorebi.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    ConfigurationService configurationService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories(){
        Optional<List<Category>> categoryList = configurationService.getAllCategories();
        if (categoryList.isEmpty()){
            return new ResponseEntity("Empty", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(categoryList.get(), HttpStatus.OK);
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryName") String categoryName){
        Optional<Category> category = configurationService.getCategory(categoryName);
        if (category.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(category.get(), HttpStatus.OK);
    }
    @GetMapping("/config")
    public ResponseEntity<?> getConfig(){
        Optional<Configuration> configuration = configurationService.getConfig();
        if (configuration.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configuration.get(), HttpStatus.OK);
    }
}
