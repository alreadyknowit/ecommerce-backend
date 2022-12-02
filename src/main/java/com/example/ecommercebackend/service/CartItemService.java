package com.example.ecommercebackend.service;

import com.example.ecommercebackend.dao.CartItemRepository;
import com.example.ecommercebackend.dto.CartItemResponseDto;
import com.example.ecommercebackend.dto.CartResponseDto;
import com.example.ecommercebackend.dto.ProductRequestDto;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.CartItem;
import com.example.ecommercebackend.model.Category;
import com.example.ecommercebackend.model.Product;
import com.example.ecommercebackend.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CategoryService categoryService;

    private final ModelMapper modelMapper;


    private final UserService userService;


    public CartItemService(CartItemRepository cartItemRepository,
                           CategoryService categoryService, ModelMapper modelMapper, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public CartItemResponseDto getCartItemResponse(int id) throws ResourceNotFoundException {
        return cartItemRepository.findById(id).map(this::mapCartItemToDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cart item with id " + id + " not found!")
                );
    }


    public CartItem getCartItem(int id)throws ResourceNotFoundException{
        return cartItemRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cart item with id " + id + " not found!")
                );
    }

    public CartResponseDto addNewCartItem(ProductRequestDto dto, int userId)
            throws ResourceNotFoundException {


        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);

        Optional<CartItem> optionalCartItem = cartItems
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId() == dto.getId())
                .findFirst();

        Product product = mapDtoToProduct(dto);
        CartItem cartItem;
        if (optionalCartItem.isPresent()) {
            cartItem = optionalCartItem.get();
            cartItem.setTotalQuantity(cartItem.getTotalQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            User user = userService.getUser(userId);
            cartItem.setUser(user);
            cartItem.setTotalQuantity(1);
            cartItems.add(cartItem);
        }
        cartItemRepository.save(cartItem);

        return mapCartToResponse(cartItems,userId);
    }


    public Product mapDtoToProduct(ProductRequestDto dto) throws ResourceNotFoundException {
        Category category = categoryService.getOneCategory(dto.getCategoryId());

        if (modelMapper.getTypeMap(ProductRequestDto.class, Product.class) == null) {
            modelMapper.createTypeMap(ProductRequestDto.class, Product.class);
        }

        Product product = modelMapper.map(dto, Product.class);
        product.setCategory(category);
        return product;
    }

    public ProductRequestDto mapProductToDto(Product product) {
        if (modelMapper.getTypeMap(Product.class, ProductRequestDto.class) == null) {
            modelMapper.createTypeMap(Product.class, ProductRequestDto.class)
                    .addMappings(mapper -> {
                        mapper.map(p -> p.getCategory().getId(), ProductRequestDto::setCategoryId);
                    });
        }
        return modelMapper.map(product, ProductRequestDto.class);
    }


    public CartItemResponseDto mapCartItemToDto(CartItem cartItem) {

        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setId(cartItem.getId());
        dto.setTotalPrice(cartItem.getTotalQuantity() * cartItem.getProduct().getUnitPrice());
        dto.setTotalQuantity(cartItem.getTotalQuantity());
        dto.setProductRequestDto(mapProductToDto(cartItem.getProduct()));

        return dto;
    }

    private CartResponseDto mapCartToResponse(List<CartItem> cartItems,int id) {

        CartResponseDto dto = new CartResponseDto();
        List<CartItemResponseDto> cartItemResponseDtos = cartItems.stream().map(this::mapCartItemToDto).collect(Collectors.toList());
        dto.setCartItems(cartItemResponseDtos);
        dto.setId(id);
        double totalPrice = cartItems.stream().mapToDouble(item -> item.getProduct().getUnitPrice() * item.getTotalQuantity()).sum();
        dto.setTotalPrice(totalPrice);
        dto.setTotalQuantity(cartItems.size());
        return dto;
    }

    public void removeProductFromCartItem(int id) throws ResourceNotFoundException {
        CartItem cartItem = getCartItem(id);
        if (cartItem.getTotalQuantity() !=1 ){
            cartItem.setTotalQuantity(cartItem.getTotalQuantity()-1);
            cartItemRepository.save(cartItem);
        }else{
            cartItemRepository.deleteById(id);
        }


    }

    public CartResponseDto getCart(int userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);
        return mapCartToResponse(cartItems,userId);
    }
}
