package com.example.ecommercebackend.controller;


import com.example.ecommercebackend.dto.CartItemResponseDto;
import com.example.ecommercebackend.dto.CartResponseDto;
import com.example.ecommercebackend.dto.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.service.CartItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {


    private final CartItemService cartItemService;


    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping()
    public CartResponseDto addNewCartItem(@RequestBody ProductRequestDto dto, @RequestParam Integer userId)
            throws ResourceNotFoundException {

        return cartItemService.addNewCartItem(dto, userId);
    }

    @GetMapping("{id}")
    public CartItemResponseDto getCartItem(@PathVariable int id) throws ResourceNotFoundException {
        return cartItemService.getCartItemResponse(id);
    }

    @DeleteMapping({"{cartId}"})
    public void removeProductFromCartItem(@PathVariable int cartId) throws ResourceNotFoundException {
         cartItemService.removeProductFromCartItem(cartId);
    }

    @GetMapping
    public CartResponseDto getCart(@RequestParam int userId){
    return cartItemService.getCart(userId);
    }

}



