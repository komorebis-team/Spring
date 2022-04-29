package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.User;
import com.itesm.komorebi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class PersonnelController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<User> findByStaffId(@PathVariable("email") String email){
        Optional<User> findPersonnel = userService.findByEmail(email);
        if (findPersonnel.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(findPersonnel.get());
    }

    @GetMapping("/supervisors")
    public ResponseEntity<List<User>> findAllSupervisors(){
        return new ResponseEntity(userService.findAllSupervisors(), HttpStatus.OK);
    }

    @GetMapping("/agents")
    public ResponseEntity<List<User>> findAllAgents(){
        return new ResponseEntity(userService.findAllAgents(), HttpStatus.OK);
    }

    @GetMapping("/managers")
    public ResponseEntity<List<User>> findAllManagers(){
        return new ResponseEntity(userService.findAllManagers(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> insert(@RequestBody User user){
        Optional<User> insertedPersonnel = userService.insert(user);
        if (insertedPersonnel.isEmpty()){
            return new ResponseEntity("Already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(insertedPersonnel.get(), HttpStatus.CREATED);
    }

    @PutMapping("/save")
    public ResponseEntity<User> update(@RequestBody User user){
        Optional<User> updatePersonnel = userService.update(user);
        if (updatePersonnel.isEmpty()){
            return new ResponseEntity("Do not exist", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(updatePersonnel.get(), HttpStatus.CREATED);
    }
}
