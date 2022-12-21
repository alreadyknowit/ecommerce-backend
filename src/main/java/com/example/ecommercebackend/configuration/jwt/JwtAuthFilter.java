package com.example.ecommercebackend.configuration.jwt;

import com.example.ecommercebackend.dto.request.AuthRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtConfiguration jwtConfiguration;


    public  JwtAuthFilter(AuthenticationManager authenticationManager, JwtConfiguration jwtConfiguration) {
        this.authenticationManager = authenticationManager;
        this.jwtConfiguration = jwtConfiguration;
        this.setFilterProcessesUrl("/api/v1/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AuthRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), AuthRequestDto.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    requestDto.getUsername(),
                    requestDto.getPassword()
            );

             authentication =authenticationManager.authenticate(authentication);
            return authentication;

        } catch (IOException e) {
            System.out.println("HATA FIRLATILDI!");
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(3)))
                .signWith(jwtConfiguration.secretKey())
                .compact();

        response.addHeader(jwtConfiguration.getAuthorizationHeader(), jwtConfiguration.getTokenPrefix() + token);

    }
}
