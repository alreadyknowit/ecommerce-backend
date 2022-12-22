package com.example.ecommercebackend.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class AuthException extends BadCredentialsException {
    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
