package com.example.ecommercebackend.controller.appuser;

import com.example.ecommercebackend.dto.request.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Product;
import com.example.ecommercebackend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")
    public Product getOneProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        return productService.getOneProduct(id);
    }

    @PutMapping("{id}") @Deprecated
    public Product updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto,
                                                                    @PathVariable Integer id) throws ResourceNotFoundException {
        return productService.updateProduct(productRequestDto, id);
    }

    @PostMapping @Deprecated
    public Product addProduct(@Valid @RequestBody ProductRequestDto product) throws ResourceNotFoundException {
        return productService.addProduct(product);
    }

    @PostMapping("/addAll") @Deprecated
    public List<Product> addProduct(@RequestBody List<ProductRequestDto> products) throws ResourceNotFoundException {
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
