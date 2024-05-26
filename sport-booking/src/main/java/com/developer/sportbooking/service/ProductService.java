package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> findAllProduct();

    Product findProductById(Integer id);

    Product findProductByCourtId(Integer id);

    Product updateProductById(Product product, Integer id);

    void deleteProductById(Integer id);
}
