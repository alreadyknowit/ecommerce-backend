package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Category;
import com.example.ecommercebackend.configuration.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping@Deprecated
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

    @PutMapping("{id}")@Deprecated
    public Category updateCategory(@PathVariable int id,@Valid  @RequestBody Category category) throws ResourceNotFoundException {
        return categoryService.updateCategory(category, id);
    }

    @PostMapping("/addAll") @Deprecated
    public List<Category> insertAll(@RequestBody List<Category> categories){
        return categoryService.insertAll(categories);
    }


}
