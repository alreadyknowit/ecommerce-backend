package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.configuration.jwt.JwtUtils;
import com.example.ecommercebackend.dto.request.AuthRequestDto;
import com.example.ecommercebackend.dto.response.AuthResponse;
import com.example.ecommercebackend.exception.AuthException;
import com.example.ecommercebackend.exception.ResourceAlreadyExistException;
import com.example.ecommercebackend.configuration.service.AppUserService;
import com.example.ecommercebackend.model.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    private final JwtUtils jwtUtils;


    public AuthController(AppUserService appUserService,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody AuthRequestDto dto) throws ResourceAlreadyExistException {
        System.out.println("deneme");
        return appUserService.registerUser(dto);

    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequestDto dto)throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        );
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(authToken);
        }catch (Exception e){
            throw new AuthException("Username or password incorrect.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        AppUser user = appUserService.loadUserByUsername(dto.getUsername());
        return new AuthResponse(user.getId(),user.getUsername(),token);

    }


}
