package com.ecomapp.Service;

import com.ecomapp.DTOs.ProductRequest;
import com.ecomapp.DTOs.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse createProduct(ProductRequest productRequest);

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> searchProduct(String keyword);
}
