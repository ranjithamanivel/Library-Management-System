package com.example.Library.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String message;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String token,String message) {
        this.token = token;
        this.message = message;
    }
}
