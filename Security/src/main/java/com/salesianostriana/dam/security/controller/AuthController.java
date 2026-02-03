package com.salesianostriana.dam.security.controller;

import com.salesianostriana.dam.security.dto.AuthResponse;
import com.salesianostriana.dam.security.dto.LoginRequest;
import com.salesianostriana.dam.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
