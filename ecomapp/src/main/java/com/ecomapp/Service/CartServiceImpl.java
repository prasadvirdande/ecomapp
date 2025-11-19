package com.ecomapp.Service;

import com.ecomapp.DTOs.CartRequest;
import com.ecomapp.Model.CartItem;
import com.ecomapp.Model.Product;
import com.ecomapp.Model.User;
import com.ecomapp.Repository.CartRepository;
import com.ecomapp.Repository.ProductRepository;
import com.ecomapp.Repository.USerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final USerRepository userRepository;

    @Override
    public void addProductToCart(String userId, CartRequest cartRequest) {

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cartRepository.findByUserAndProduct(user, product);

        if (existingItem != null) {
            existingItem.setQuantity((int) (existingItem.getQuantity()+cartRequest.getQuantity()));
            existingItem.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity()))
            );

            cartRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantity(Math.toIntExact(cartRequest.getQuantity()));
            newItem.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(cartRequest.getQuantity()))
            );

            cartRepository.save(newItem);
        }


    }
    @Override
    public boolean deleteItemFromCart(String userId, String productId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found")));

       Optional< Product> product = Optional.ofNullable(productRepository.findById(Long.valueOf(productId))
               .orElseThrow(() -> new RuntimeException("Product not found")));
        if(user.isPresent() && product.isPresent()){
            cartRepository.deleteByUserAndProduct(user.get(), product.get());
         return  true;
        }
        return false;

    }

    @Override
    public List<CartItem> getCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserId(user);
    }


}

