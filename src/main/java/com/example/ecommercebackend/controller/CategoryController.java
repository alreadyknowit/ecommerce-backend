package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Category;
import com.example.ecommercebackend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category insertCategory(@Valid @RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    @GetMapping("{id}")
    public Category getOneCategory(@PathVariable int id) throws ResourceNotFoundException {
        return categoryService.getOneCategory(id);
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PutMapping("{id}")
    public Category updateCategory(@PathVariable int id,@Valid  @RequestBody Category category) throws ResourceNotFoundException {
        return categoryService.updateCategory(category, id);
    }

    @PostMapping("/addAll")
    public List<Category> insertAll(@RequestBody List<Category> categories){
        return categoryService.insertAll(categories);
    }


}
