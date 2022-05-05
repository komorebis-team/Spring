package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.models.Configuration;
import com.itesm.komorebi.services.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@SecurityScheme(name = "komorebi_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    ConfigurationService configurationService;

    @Operation(summary = "Return all the categories", description = "Return all the categories and their associeated" +
            "tags from the configuration table")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories(){
        Optional<List<Category>> categoryList = configurationService.getAllCategories();
        if (categoryList.isEmpty()){
            return new ResponseEntity("Empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(categoryList.get(), HttpStatus.OK);
    }

    @Operation(summary = "Return the selected category information", description = "Receive a category name and get" +
            "the information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Category.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The category do not exist in the database",
                    content = @Content)
    })
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getCategory(
            @Parameter(description = "Name of category to search", required = true) @PathVariable("categoryName") String categoryName)
    {
        Optional<Category> category = configurationService.getCategory(categoryName);
        if (category.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(category.get(), HttpStatus.OK);
    }

    @Operation(summary = "Add a category", description = "Receive a category and add it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Configuration.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "406", description = "The category needs to have associated tags",
                    content = @Content)
    })
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(
            @Parameter(description = "The category to insert", required = true) @RequestBody Category category)
    {
        if (category.getAvailableTags().size() == 0){
            return new ResponseEntity("Cannot add category with no associated tags", HttpStatus.NOT_ACCEPTABLE);
        }
        Configuration configuration = configurationService.addCategory(category);
        if (configuration == null){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configuration, HttpStatus.CREATED);
    }
}
