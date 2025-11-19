package com.ecomapp.DTOs;

import lombok.Data;

@Data
public class CartRequest {

    private Long productId;
    private Long quantity;
}
