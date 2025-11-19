package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;
import com.ecomapp.Model.CartItem;
import com.ecomapp.Model.Product;
import com.ecomapp.Model.User;
import com.ecomapp.Repository.CartRepository;
import com.ecomapp.Repository.ProductRepository;
import com.ecomapp.Repository.USerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final USerRepository userRepository;

    @Override
    public boolean addProductToCart(String userId, CartRequest cartRequest) {

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cartRepository.findByUserAndProduct(user, product);

        if (existingItem != null) {
            existingItem.setQuantity((int) (existingItem.getQuantity()+cartRequest.getQuantity()));
            existingItem.setTotalPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity()))
            );

            cartRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantity(Math.toIntExact(cartRequest.getQuantity()));
            newItem.setTotalPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(cartRequest.getQuantity()))
            );

            cartRepository.save(newItem);
        }
        return false;
    }
}
