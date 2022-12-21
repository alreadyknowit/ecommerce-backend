package com.example.ecommercebackend.controller.appuser;


import com.example.ecommercebackend.dto.response.CartItemResponse;
import com.example.ecommercebackend.dto.response.CartResponse;
import com.example.ecommercebackend.dto.request.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.service.CartItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cartItem")
public class CartItemController {


    private final CartItemService cartItemService;


    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping()
    public CartItemResponse addNewCartItem(@RequestBody ProductRequestDto dto, @RequestParam Integer userId)
            throws ResourceNotFoundException {

        return cartItemService.addNewCartItem(dto, userId);
    }

    @GetMapping("{id}")
    public CartItemResponse getCartItem(@PathVariable int id) throws ResourceNotFoundException {
        return cartItemService.getCartItemResponse(id);
    }

    @DeleteMapping({"{cartId}"})
    public void removeProductFromCartItem(@PathVariable int cartId) throws ResourceNotFoundException {
         cartItemService.removeProductFromCartItem(cartId);
    }

    @GetMapping
    public CartResponse getCart(@RequestParam int userId){
    return cartItemService.getCart(userId);
    }

}



