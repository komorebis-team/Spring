package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Recording;
import com.itesm.komorebi.models.RecordingKey;
import com.itesm.komorebi.models.User;
import com.itesm.komorebi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Return all the users", description = "Return all the users from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = User.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> userList = userService.findAll();
        if (userList.isEmpty()){
            return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @Operation(summary = "Return a user", description = "Return a user with the specified email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = User.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "There is not a user with the given email",
                    content = @Content)
    })
    @GetMapping("/find/email/{email}")
    public ResponseEntity<User> findByStaffId(
            @Parameter(description = "The email to look for", required = true) @PathVariable("email") String email){
        Optional<User> findPersonnel = userService.findByEmail(email);
        if (findPersonnel.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(findPersonnel.get());
    }

    @Operation(summary = "Return all the supervisors", description = "Return all the supervisors from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = User.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/supervisors")
    public ResponseEntity<List<User>> findAllSupervisors(){
        return new ResponseEntity(userService.findAllSupervisors(), HttpStatus.OK);
    }

    @Operation(summary = "Return all the agents", description = "Return all the agents from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = User.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/agents")
    public ResponseEntity<List<User>> findAllAgents(){
        return new ResponseEntity(userService.findAllAgents(), HttpStatus.OK);
    }

    @Operation(summary = "Return all the administrators", description = "Return all the administrators from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Recording.class)),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "204", description = "Successful operation but there is no content",
                    content = @Content)
    })
    @GetMapping("/admins")
    public ResponseEntity<List<User>> findAllAdmin(){
        return new ResponseEntity(userService.findAllAdmin(), HttpStatus.OK);
    }

    @Operation(summary = "Add a user", description = "Add the given user to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = User.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "409", description = "The given user already exists",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<User> insert(
            @Parameter(description = "The user to be inserted", required = true) @RequestBody User user){
        Optional<User> insertedPersonnel = userService.insert(user);
        if (insertedPersonnel.isEmpty()){
            return new ResponseEntity("Already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(insertedPersonnel.get(), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a user", description = "Update the data of the given user to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = User.class),
                    mediaType = "application/json"
            )}),
            @ApiResponse(responseCode = "404", description = "The given user does not exist",
                    content = @Content)
    })
    @PutMapping("/save")
    public ResponseEntity<User> update(
            @Parameter(description = "The user to be updated", required = true) @RequestBody User user){
        Optional<User> updatePersonnel = userService.update(user);
        if (updatePersonnel.isEmpty()){
            return new ResponseEntity("Does not exist", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(updatePersonnel.get(), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a user", description = "Delete a user with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content)
    })
    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(
            @Parameter(description = "The email of the user", required = true) @PathVariable("email") String email){
       userService.deleteUser(email);
        return new ResponseEntity("Changes applied", HttpStatus.OK);
    }
}
