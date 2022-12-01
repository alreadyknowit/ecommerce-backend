package com.example.ecommercebackend.dao;

import com.example.ecommercebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}