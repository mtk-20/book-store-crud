package com.example.book_store_management.controller;

import com.example.book_store_management.dto.LoginDto;
import com.example.book_store_management.dto.RegisterDto;
import com.example.book_store_management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto, "USER");
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto, "ADMIN");
        return ResponseEntity.ok("Admin registered successfully!");
    }
}
