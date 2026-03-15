package com.example.Library.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));
        String token = header.substring(7);
        try {
            String username = jwtService.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails)) {
                    // Extract roles from JWT
                    List<String> roles = jwtService.extractRoles(token);

                  // Convert roles to authorities
                    var authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList();

                 // Create authentication token
                    var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );

                 // Set authentication
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    // Debug logs (very useful)
                    System.out.println("Authenticated User: " + username);
                    System.out.println("Authorities: " + authorities);

//                    var authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    System.out.println("Extracted Username: " + username);
//                    System.out.println("Authorities: " + userDetails.getAuthorities());
//                    System.out.println("Token Valid: " + jwtService.isTokenValid(token, userDetails));
//                   // var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    System.out.println("Authorities inside SecurityContext: " +
//                            SecurityContextHolder.getContext()
//                                    .getAuthentication()
//                                    .getAuthorities());
                }
            }
        } catch (JwtException ex) {
            // invalid token — clear context (optionally log)
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
        System.out.println("JWT FILTER CALLED");
    }
}
