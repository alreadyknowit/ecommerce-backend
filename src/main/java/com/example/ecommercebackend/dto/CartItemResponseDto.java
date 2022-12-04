package com.example.ecommercebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemResponseDto {

    private int id;

    @JsonProperty("product")
    private ProductRequestDto productRequestDto;

    private int totalQuantity;

    private double totalPrice;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductRequestDto getProductRequestDto() {
        return productRequestDto;
    }

    public void setProductRequestDto(ProductRequestDto productRequestDto) {
        this.productRequestDto = productRequestDto;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
