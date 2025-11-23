package com.example.Library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Disable CSRF
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/**").permitAll()
                                .requestMatchers("/api/books/**").hasRole("ADMIN")
                                .requestMatchers("/api/users/**").hasRole("ADMIN")
                                .requestMatchers("/api/borrowings/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated()
//                        .requestMatchers("/","/api/auth/**", "/auth/register",
//                                "/auth/login",
//                                "/books/**",
//                                "/api/books",
//                                "/api/books/**",
//                                "/api/users/**",
//                               "/api/users",
//                                "/api/borrowing/**",
//                                "/api/borrowings/**",
//                                "api/borowings",
//                                "/api/borrowing/borrow/**",
//                                "/api/borrowings/return/**"
//                                ).permitAll() // ✅ allow everything under /api/auth
//                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
