package com.ecomapp.Service;

import com.ecomapp.DTOs.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(String userId);
}
