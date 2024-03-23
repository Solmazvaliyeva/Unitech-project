package com.uni.project.auth.controller;

import com.uni.project.auth.dto.RegisterRequest;
import com.uni.project.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.registerUser(request));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.login(request));
    }
}
