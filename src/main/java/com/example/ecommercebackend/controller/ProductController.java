package com.example.ecommercebackend.controller;

import com.example.ecommercebackend.dto.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Product;
import com.example.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")
    public com.example.ecommercebackend.model.Product getOneProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        return productService.getOneProduct(id);
    }

    @PutMapping("{id}")
    public com.example.ecommercebackend.model.Product updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable Integer id) throws ResourceNotFoundException {
        return productService.updateProduct(productRequestDto, id);
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody ProductRequestDto product) throws ResourceNotFoundException {
        return productService.addProduct(product);
    }

    @PostMapping("/addAll")
    public List<com.example.ecommercebackend.model.Product> addProduct(@RequestBody List<ProductRequestDto> products) throws ResourceNotFoundException {
        return productService.addAllProduct(products);
    }

    @GetMapping
    public List<com.example.ecommercebackend.model.Product> getProductsByCategoryId(@RequestParam Optional<Integer> categoryId) throws ResourceNotFoundException {
        if (categoryId.isPresent()) {
            return productService.getProductsByCategoryId(categoryId.get());
        }
        return productService.getProducts();
    }


}
