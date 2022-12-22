package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.dto.response.AppUserResponseDto;
import com.example.ecommercebackend.model.AppUser;
import com.example.ecommercebackend.configuration.service.AppUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json", consumes = "application/json")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("{id}")
    public AppUserResponseDto getUser(@PathVariable Long id) throws Exception {
        AppUser user = appUserService.getUserById(id);
        return new AppUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.isEnabled()
        );
    }
}
