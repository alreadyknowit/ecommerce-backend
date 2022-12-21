package com.example.ecommercebackend.configuration.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {


    private final JwtConfiguration jwtConfiguration;

    public JwtTokenVerifier(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader= request.getHeader(jwtConfiguration.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfiguration.getTokenPrefix())){
            filterChain.doFilter(request,response);
            return;
        }

        //Extract the token from the header
       try{
           String token = authorizationHeader.replace(jwtConfiguration.getTokenPrefix(), "");

           Jws<Claims> claimsJws = Jwts.parser()
                   .setSigningKey(jwtConfiguration.secretKey())
                   .parseClaimsJws(token);

           Claims body = claimsJws.getBody();
           String username=body.getSubject();
           List<Map<String,String>> authorities = (List<Map<String,String>>) body.get("authorities");

           Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                   .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                   .collect(Collectors.toSet());

           Authentication authentication = new UsernamePasswordAuthenticationToken(
                   username,
                   null,
                   simpleGrantedAuthorities
           );

           SecurityContextHolder.getContext().setAuthentication(authentication);
       }catch (JwtException e){
            throw new IllegalStateException("Token cannot be verified!");
       }

       filterChain.doFilter(request,response);
    }
}
