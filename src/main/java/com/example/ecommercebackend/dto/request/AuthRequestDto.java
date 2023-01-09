package com.example.ecommercebackend.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthRequestDto {

    @NotNull(message = "Username must be inserted.")
    @NotBlank(message = "Invalid username.")

    private String username;
    @NotBlank(message = "Invalid password.")
    @NotNull(message = "Password must be inserted.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
