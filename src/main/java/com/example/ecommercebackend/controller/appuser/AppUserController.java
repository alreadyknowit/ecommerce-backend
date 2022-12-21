package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.dto.response.AppUserResponseDto;
import com.example.ecommercebackend.model.AppUser;
import com.example.ecommercebackend.service.AppUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
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
