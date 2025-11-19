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
    public ResponseEntity<Void> addProductToCart(@RequestHeader String userId, @RequestBody CartRequest cartRequest){
      cartService.addProductToCart(userId, cartRequest);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
