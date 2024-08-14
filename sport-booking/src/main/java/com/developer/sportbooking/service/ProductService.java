package com.developer.sportbooking.service;

import com.developer.sportbooking.dto.ProductDto;
import com.developer.sportbooking.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(ProductDto product);

    List<Product> findAllProduct();

    Product findProductById(Long id);

    Product findProductByCourtId(Long id);

    Product updateProductById(Product product, Long id);

    void deleteProductById(Long id);
}
