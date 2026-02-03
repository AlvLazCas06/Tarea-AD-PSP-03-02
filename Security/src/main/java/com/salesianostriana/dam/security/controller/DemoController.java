package com.salesianostriana.dam.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    /**
     * Endpoint público - No requiere autenticación
     */
    @GetMapping("/public/hello")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        return ResponseEntity.ok(Map.of(
                "message", "Este es un endpoint público",
                "description", "No requiere autenticación para acceder"
        ));
    }

    /**
     * Endpoint protegido - Requiere autenticación JWT
     */
    @GetMapping("/secured/hello")
    public ResponseEntity<Map<String, String>> securedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.ok(Map.of(
                "message", "Este es un endpoint protegido",
                "description", "Requiere autenticación JWT para acceder",
                "user", username
        ));
    }

    /**
     * Endpoint protegido adicional - Solo para administradores
     */
    @GetMapping("/admin/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> adminEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.ok(Map.of(
                "message", "Este es un endpoint de administrador",
                "description", "Solo accesible para usuarios con rol ADMIN",
                "user", username
        ));
    }

}
