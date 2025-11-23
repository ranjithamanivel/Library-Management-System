package com.example.Library.service;

import com.example.Library.dto.*;
import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.management.relation.Relation;
import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    //SECRET_KEY should be long enough (at least 32 characters for HS256)
    private final String SECRET_KEY="library_secret_key_123456789012345678901234567890";

    public AuthService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public RegisterResponse register(RegisterRequest req){
        if(userRepository.findByEmail(req.getEmail()).isPresent()){
           throw new RuntimeException("This email is already registered");
        }
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(User.Role.USER);
        user.setRole(User.Role.ADMIN);

        userRepository.save(user);
        return new RegisterResponse("User registered successfully");
    }
    public AuthenticationResponse login(LoginRequest req){
       Optional <User> userOpt=userRepository.findByEmail(req.getEmail());
       if(userOpt.isEmpty() || !passwordEncoder.matches(req.getPassword(),userOpt.get().getPassword())){
        throw new RuntimeException("Invalid email or password");
    }
       Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

         User user=userOpt.get();
        String token= Jwts.builder()
          .setSubject(userOpt.get().getEmail())
                 .claim("role",user.getRole())
                 .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis()+86400000)) // 1 day expiration
          .signWith(key,SignatureAlgorithm.HS256)
          .compact();
         return new AuthenticationResponse(token,"Login successful");
     }
}
