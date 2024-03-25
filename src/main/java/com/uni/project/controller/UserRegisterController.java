package com.uni.project.controller;

import com.uni.project.models.RegisterRequest;
import com.uni.project.service.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserRegisterController {

    private final UserRegisterService service;

    public UserRegisterController(UserRegisterService service) {
        this.service = service;
    }

    @PostMapping("/register/user")
    public ResponseEntity<RegisterRequest> registerUser(@Valid  @RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.registerUser(request));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.login(request));
    }
}
