package com.example.Library.service;

import com.example.Library.dto.*;
import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;
import com.example.Library.config.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // ================= REGISTER =================
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRole() == null) {
            user.setRole(User.Role.USER);
        } else {
            user.setRole(User.Role.valueOf(request.getRole().toUpperCase()));
        }

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }

    // ================= LOGIN =================
    public AuthenticationResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        List<String> roles = List.of("ROLE_" + user.getRole().name());

         // Generate token with roles claim
        String token = jwtService.generateToken(
                user.getEmail(),
                roles
        );

        // Debug print (helps verify role inside token)
        System.out.println("Login User: " + user.getEmail());
        System.out.println("Roles in token: " + roles);

        return new AuthenticationResponse(token, "Login successful");
    }
}
