package com.example.ecommercebackend.dao;

import com.example.ecommercebackend.model.AppUserGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserGrantedAuthorityRepository extends JpaRepository<AppUserGrantedAuthority, Long> {
}