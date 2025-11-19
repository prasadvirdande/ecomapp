package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;

public interface CartService   {
    void addProductToCart(String userId, CartRequest cartRequest);
}
