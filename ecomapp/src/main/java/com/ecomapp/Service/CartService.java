package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;
import com.ecomapp.Model.CartItem;

import java.util.List;

public interface CartService   {
  void addProductToCart(String userId, CartRequest cartRequest);


    boolean deleteItemFromCart(String userId, String productId);

    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
