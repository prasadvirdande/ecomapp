package com.ecomapp.Repository;

import com.ecomapp.Model.CartItem;
import com.ecomapp.Model.Product;
import com.ecomapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);


    List<CartItem> findByUserId(User user);

    CartItem deleteByUserAndProduct(User user, Product product);
}
