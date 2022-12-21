package com.example.ecommercebackend.controller.management;

import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Category;
import com.example.ecommercebackend.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/categories")
public class CategoryManagementController {

    private final CategoryService categoryService;

    public CategoryManagementController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category updateCategory(@PathVariable int id,@Valid  @RequestBody Category category) throws ResourceNotFoundException {
        return categoryService.updateCategory(category, id);
    }

    @PostMapping("/addAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> insertAll(@RequestBody List<Category> categories){
        return categoryService.insertAll(categories);
    }


}
