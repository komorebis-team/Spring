package com.itesm.komorebi.controllers;

import com.itesm.komorebi.config.JwtUtils;
import com.itesm.komorebi.dto.UserDTO;
import com.itesm.komorebi.models.JwtResponse;
import com.itesm.komorebi.models.User;
import com.itesm.komorebi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails u= userService.loadUserByUsername(user.getUsername());
        List<String> roles = u.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,"Bearer"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth);
    }
}
