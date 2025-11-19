package com.ecomapp.Controller;

import com.ecomapp.DTOs.CartRequest;
import com.ecomapp.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addProductToCart(
            @RequestHeader String userId,
            @RequestBody CartRequest cartRequest) {

        boolean isAdded = cartService.addProductToCart(userId, cartRequest);

        if (!isAdded) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Product out of stock or user not found");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Product added to cart successfully");
    }
}