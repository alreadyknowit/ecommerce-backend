package com.example.ecommercebackend.dao;

import com.example.ecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}