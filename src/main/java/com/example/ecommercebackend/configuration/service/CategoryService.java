package com.example.ecommercebackend.configuration.service;

import com.example.ecommercebackend.dao.CategoryRepository;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
    }

    public Category getOneCategory(int categoryId) throws ResourceNotFoundException {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Category with id " + categoryId + " not found! "
                )
        );
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category insertCategory( Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, int id) throws ResourceNotFoundException {
        Category foundCategory = getOneCategory(id);
        foundCategory.setCategoryName(category.getCategoryName());
        foundCategory.setSeoUrl(category.getSeoUrl());
        return categoryRepository.save(foundCategory);
    }

    public List<Category> insertAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}
