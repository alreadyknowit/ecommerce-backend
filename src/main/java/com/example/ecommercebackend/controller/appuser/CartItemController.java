package com.example.ecommercebackend.controller.appuser;


import com.example.ecommercebackend.dto.response.CartItemResponse;
import com.example.ecommercebackend.dto.response.CartResponse;
import com.example.ecommercebackend.dto.request.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.configuration.service.CartItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cartItem")
public class CartItemController {


    private final CartItemService cartItemService;


    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping()
    public CartItemResponse addNewCartItem(@RequestBody ProductRequestDto dto, @RequestParam Long userId)
            throws ResourceNotFoundException {

        return cartItemService.addNewCartItem(dto, userId);
    }

    @GetMapping("{id}")
    public CartItemResponse getCartItem(@PathVariable Long id) throws ResourceNotFoundException {
        return cartItemService.getCartItemResponse(id);
    }

    @DeleteMapping({"{cartId}"})
    public void removeProductFromCartItem(@PathVariable Long cartId) throws ResourceNotFoundException {
         cartItemService.removeProductFromCartItem(cartId);
    }

    @GetMapping
    public CartResponse getCart(@RequestParam Long userId){
        //cartId= userId because each user has only one cart.
    return cartItemService.getCart(userId);
    }

}



