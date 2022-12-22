package com.example.ecommercebackend.dto.response;

public class AuthResponse {

    private Long userId;
    private String username;
    private String accessToken;

    public AuthResponse(Long userId, String username, String accessToken) {
        this.userId = userId;
        this.username = username;
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
