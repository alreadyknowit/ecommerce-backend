package com.example.ecommercebackend.dto;

import java.util.List;

public class CartResponseDto {

    private int id;

    private List<CartItemResponseDto> cartItems;

    private int totalQuantity;

    private double totalPrice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItemResponseDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDto> cartItems) {
        this.cartItems = cartItems;
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
