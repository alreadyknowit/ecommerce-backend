package com.example.ecommercebackend.configuration.service;

import com.example.ecommercebackend.dao.AppUserRepository;
import com.example.ecommercebackend.dto.request.AuthRequestDto;
import com.example.ecommercebackend.dto.response.AppUserResponseDto;
import com.example.ecommercebackend.exception.ResourceAlreadyExistException;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.AppUser;
import com.example.ecommercebackend.model.AppUserGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findAppUserByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(String.format("%s username not found", username)));
    }

    public AppUser getUserById(Long id) throws ResourceNotFoundException {
        if (id != null) {
            return appUserRepository.findAppUserById(id).orElseThrow(
                    () -> new ResourceNotFoundException(String.format("User with id %s not found", id))
            );
        }
        throw new ResourceNotFoundException("Id must be specified");
    }

    public AppUserResponseDto createUser(AuthRequestDto dto) throws ResourceAlreadyExistException {
        if (!isUserExists(dto.getUsername())) {
            AppUser appUser = new AppUser();
            appUser.setUsername(dto.getUsername());
            appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            AppUserGrantedAuthority authority = new AppUserGrantedAuthority();
            authority.setAuthority("ROLE_USER");
            authority.setPermissions(new HashSet<>());
            Set<AppUserGrantedAuthority> authorities = new HashSet<>();
            authorities.add(authority);
            appUser.setGrantedAuthorities(authorities);
            AppUser savedUser = appUserRepository.save(appUser);

            return new AppUserResponseDto(
                    savedUser.getId(),
                    savedUser.getUsername(),
                    savedUser.isEnabled()
            );
        }
        throw new ResourceAlreadyExistException(String.format("User with username %s is already exists.", dto.getUsername()));
    }

    public void updateUser(UserDetails user) {
        appUserRepository.save((AppUser) user);
    }

    public void deleteUser(String username) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s username not found.", username)));
        appUserRepository.delete(appUser);

    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        AppUser appUser = appUserRepository.findAppUserByPassword(oldPassword)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid password."));
        appUser.setPassword(newPassword);
        appUserRepository.save(appUser);

    }
    public boolean isUserExists(String username) {
        return appUserRepository.findAppUserByUsername(username).isPresent();
    }


}
