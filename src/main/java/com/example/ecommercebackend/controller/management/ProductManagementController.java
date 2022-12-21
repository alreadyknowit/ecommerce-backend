package com.example.ecommercebackend.controller.management;

import com.example.ecommercebackend.dto.request.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Product;
import com.example.ecommercebackend.configuration.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/products")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public class ProductManagementController {


    private final ProductService productService;

    public ProductManagementController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")

    public Product getOneProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        return productService.getOneProduct(id);
    }

    @PutMapping("{id}")

    public Product updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto,
                                 @PathVariable Integer id) throws ResourceNotFoundException {
        return productService.updateProduct(productRequestDto, id);
    }

    @PostMapping
    public Product addAllProducts(@Valid @RequestBody ProductRequestDto product) throws ResourceNotFoundException {
        return productService.addProduct(product);
    }

    @PostMapping("/addAll")
    public List<Product> addAllProducts(@RequestBody List<ProductRequestDto> products) throws ResourceNotFoundException {
        return productService.addAllProduct(products);
    }

    @GetMapping

    public List<Product> getProductsByCategoryId(@RequestParam Optional<Integer> categoryId) {
        if (categoryId.isPresent()) {
            return productService.getProductsByCategoryId(categoryId.get());
        }
        return productService.getProducts();
    }


}
