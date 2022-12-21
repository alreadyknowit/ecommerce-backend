package com.example.ecommercebackend.model;


import javax.persistence.*;
import java.util.List;

@Entity @Deprecated
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CartItem> cartItem;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
}
