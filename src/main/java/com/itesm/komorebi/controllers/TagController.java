package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.models.Configuration;
import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.Tag;
import com.itesm.komorebi.services.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;

@SecurityScheme(name = "komorebi_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    @Autowired
    ConfigurationService configurationService;

    @Operation(summary = "Add a tag", description = "Receive a tag and add it to the category list it's associated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Configuration.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The configuration data cannot be found", content = @Content)
    })
    @PutMapping("/add")
    public ResponseEntity<?> addTag(@Parameter(description = "The tag to insert", required = true) @RequestBody Tag tag)
    {
        Configuration configuration = configurationService.addTag(tag.getCategoryName(), tag.getName());
        if (configuration == null){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configuration, HttpStatus.CREATED);
    }

    @Operation(summary = "Return all the tags", description = "Return all the unique tags from the configuration table")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        Optional<HashSet<String>> tags = configurationService.getAllTags();
        if (tags.isEmpty()){
            return new ResponseEntity("Don not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tags.get(), HttpStatus.OK);
    }

    @Operation(summary = "Remove a tag", description = "Remove a tag from the category's available tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Configuration.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The configuration data cannot be found",
                    content = @Content)
    })
    @PutMapping("/remove")
    public ResponseEntity<?> deleteTag(
            @Parameter(description = "The tag to be removes", required = true) @RequestBody Tag tag){
        Configuration configuration = configurationService.deleteTag(tag.getCategoryName(), tag.getName());
        if (configuration == null){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configuration, HttpStatus.CREATED);
    }
}
