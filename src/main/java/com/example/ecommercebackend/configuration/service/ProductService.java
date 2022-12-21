package com.example.ecommercebackend.configuration.service;


import com.example.ecommercebackend.dao.ProductRepository;
import com.example.ecommercebackend.dto.request.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.Category;
import com.example.ecommercebackend.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public Product getOneProduct(Integer id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with " + id + " not found.")
        );
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(ProductRequestDto productDto, int id) throws ResourceNotFoundException {
        return productRepository.save(mapDtoToProduct(productDto));
    }

    public Product addProduct(ProductRequestDto dto) throws ResourceNotFoundException {
        Product product = mapDtoToProduct(dto);
        return productRepository.save(product);
    }


    public List<Product> addAllProduct(List<ProductRequestDto> productsDto) throws ResourceNotFoundException, RuntimeException {
        List<Product> productList = new ArrayList<>();

        productsDto.forEach(dto ->
                {
                    try {
                        productList.add(mapDtoToProduct(dto));
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return productRepository.saveAll(productList);
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }


    //mapper
    public Product mapDtoToProduct(ProductRequestDto dto) throws ResourceNotFoundException {

        if (modelMapper.getTypeMap(ProductRequestDto.class, Product.class) == null) {
            modelMapper.createTypeMap(ProductRequestDto.class, Product.class);
        }
        Product product = modelMapper.map(dto, Product.class);
        Category category = categoryService.getOneCategory(dto.getCategoryId());
        product.setCategory(category);
        return product;
    }

}
