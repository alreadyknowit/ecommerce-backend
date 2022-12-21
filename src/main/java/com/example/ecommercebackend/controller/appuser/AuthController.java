package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.dto.request.AuthRequestDto;
import com.example.ecommercebackend.exception.ResourceAlreadyExistException;
import com.example.ecommercebackend.configuration.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class AuthController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;


    public AuthController(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    public void register(@RequestBody AuthRequestDto dto) throws ResourceAlreadyExistException {
        appUserService.createUser(dto);
    }


}
