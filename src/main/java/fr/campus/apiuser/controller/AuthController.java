package fr.campus.apiuser.controller;

import fr.campus.apiuser.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        try {
            String username = request.get("username");
            String password = request.get("password");

            // Vérifie le username et le mot de passe
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Si on arrive ici, l'authentification est réussie
            String token = jwtService.generateToken(username, List.of("USER"));

            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {

            // Authentification échouée
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}