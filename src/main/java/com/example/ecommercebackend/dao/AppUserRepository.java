package com.example.ecommercebackend.dao;

import com.example.ecommercebackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

  Optional<AppUser> findAppUserByUsername(String username);
  Optional<AppUser> findAppUserByPassword(String password);
  Optional<AppUser> findAppUserById(Long id);
}
