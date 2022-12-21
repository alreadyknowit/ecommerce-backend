package com.example.ecommercebackend.configuration.security;

import com.example.ecommercebackend.configuration.jwt.JwtConfiguration;
import com.example.ecommercebackend.configuration.jwt.JwtTokenVerifier;
import com.example.ecommercebackend.configuration.jwt.JwtAuthFilter;
import com.example.ecommercebackend.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    private final AppUserService appUserService;
    private final JwtConfiguration jwtConfiguration;


    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
                                            AppUserService appUserService,
                                            JwtConfiguration jwtConfiguration) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthFilter(authenticationManager(),jwtConfiguration))
                .addFilterAfter(new JwtTokenVerifier(jwtConfiguration), JwtAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/register").permitAll()
                .anyRequest()
                .authenticated();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(appUserService);
        return daoAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails user = User
                .builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities(APPUSER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }*/
}
