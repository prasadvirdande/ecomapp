package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;

public interface CartService   {
    boolean addProductToCart(String userId, CartRequest cartRequest);

    boolean addProductToCart(String userId, CartRequest cartRequest);
}
