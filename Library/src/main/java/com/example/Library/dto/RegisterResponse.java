package com.example.Library.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String message;

    public RegisterResponse() {}

    public RegisterResponse(String message) {
        this.message = message;
    }
}
