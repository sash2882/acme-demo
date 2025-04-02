package com.acme.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginResponse {
    private String email;
    private String firstName;
    private String lastName;
    private boolean newsletterOptIn;
    private LocalDateTime createdAt;
    private String username;
    private String accessToken;
    private int expiresIn;
    private String refreshToken;
}
