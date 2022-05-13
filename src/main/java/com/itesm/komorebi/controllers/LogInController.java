package com.itesm.komorebi.controllers;

import com.itesm.komorebi.config.JwtUtils;
import com.itesm.komorebi.dto.UserDTO;
import com.itesm.komorebi.models.JwtResponse;
import com.itesm.komorebi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/auth/")
public class LogInController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Operation(summary = "Authentication", description = "Return a bearer token if user credentials are valid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = JwtResponse.class),
                    mediaType = "application/json"
            )})
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Parameter(description = "The user credentials", required = true) @RequestBody UserDTO user) {
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

    @Operation(summary = "Get user details", description = "Return the user profile information with a valid token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(
                    schema = @Schema(implementation = Authentication.class),
                    mediaType = "application/json"
            )})
    })
    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth);
    }
}
