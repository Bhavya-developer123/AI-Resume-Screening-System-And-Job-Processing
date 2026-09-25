package com.example.demo.controller;

import com.example.demo.service.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
public class JwtTestController {

    private final JwtService jwtService;

    public JwtTestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String email) {

        return jwtService.generateToken(email);
    }

    @GetMapping("/validate")
    public String validate(@RequestParam String token) {

        return String.valueOf(
                jwtService.validateToken(token)
        );
    }

    @GetMapping("/email")
    public String email(@RequestParam String token) {

        return jwtService.extractEmail(token);
    }
}