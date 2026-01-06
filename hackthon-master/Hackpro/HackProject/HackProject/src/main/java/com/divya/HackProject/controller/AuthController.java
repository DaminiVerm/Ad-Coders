package com.divya.HackProject.controller;

import com.divya.HackProject.DTO.RegisterRequest;
import com.divya.HackProject.Repo.UserRepository;
import com.divya.HackProject.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        if (repository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("User Already Exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        repository.save(user);

        return ResponseEntity.ok("Registered Successfully");
    }

    @GetMapping("/validate")
    public Map<String, Object> validate(Authentication auth) {

        return Map.of(
                "status", "VALID",
                "email", auth.getName(),
                "roles", auth.getAuthorities()
        );
    }
}
