package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.service.JwtService;

import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;
    public UserController(
        UserService userService,
        AuthenticationManager authenticationManager,
        JwtService jwtService) {

    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
}
    @PostMapping("/register")
public User registerUser(@Valid @RequestBody UserRequestDto request) {
    return userService.createUser(request);
}
@PostMapping("/login")
public String login(
        @Valid @RequestBody LoginRequestDto request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    return jwtService.generateToken(request.getEmail());
}
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDto user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
