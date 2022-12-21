package com.example.ecommercebackend.dto.response;


public class AppUserResponseDto {
    private final Long userId;
    private final String username;
    private final boolean isEnabled;


    public AppUserResponseDto(Long userId, String username, boolean isEnabled) {
        this.userId = userId;
        this.username = username;
        this.isEnabled = isEnabled;
    }


}
