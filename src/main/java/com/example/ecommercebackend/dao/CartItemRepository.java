package com.example.ecommercebackend.dao;

import com.example.ecommercebackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUserId(Long userId);
}