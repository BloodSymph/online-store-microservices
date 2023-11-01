package com.company.productservice.service;

import com.company.productservice.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getSingleProduct(String productUrl);

    Page<ProductResponse> getProductsByCategory(
            String categoryUrl,
            Pageable pageable
    );

    Page<ProductResponse> getProductsByBrand(
            String brandUrl,
            Pageable pageable
    );

    Page<ProductResponse> searchProducts(
            String productName,
            String categoryName,
            String brandName,
            Pageable pageable
    );

}
