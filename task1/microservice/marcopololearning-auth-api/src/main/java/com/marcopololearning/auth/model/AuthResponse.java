package com.marcopololearning.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;

    public static AuthResponse of(String token) {
        return new AuthResponse(token);
    }
}
