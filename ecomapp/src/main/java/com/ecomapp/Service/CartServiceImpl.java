package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;
import com.ecomapp.Repository.CartRepository;
import com.ecomapp.Repository.ProductRepository;
import com.ecomapp.Repository.USerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final USerRepository userRepository;
    @Override
    public void addProductToCart(String userId, CartRequest cartRequest) {



    }
}
