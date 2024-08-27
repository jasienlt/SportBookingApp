package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.dto.ProductDto;
import com.developer.sportbooking.entity.Product;
import com.developer.sportbooking.repository.CourtRepo;
import com.developer.sportbooking.repository.ProductRepo;
import com.developer.sportbooking.service.CourtService;
import com.developer.sportbooking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CourtService courtService;

    @Override
    public Product saveProduct(ProductDto productDto) {
        Product product = new Product(productDto.getCourtId(),productDto.getName(), productDto.getPrice(), productDto.getAmount(),
                courtService.findCourtById(productDto.getCourtId()));
        return productRepo.save(product);
    }


    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product findProductByCourtId(Long id) {
        return null;
    }

    @Override
    public Product updateProductById(Product product, Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }
}
