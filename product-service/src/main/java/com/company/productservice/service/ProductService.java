package com.company.productservice.service;

import com.company.productservice.dto.product.ProductDetailsResponse;
import com.company.productservice.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);

    ProductDetailsResponse getSingleProductDetails(String productUrl);

    Page<ProductResponse> getProductsByCategory(
            String categoryUrl,
            Pageable pageable
    );

    Page<ProductResponse> getProductsByBrand(
            String brandUrl,
            Pageable pageable
    );

    Page<ProductResponse> searchProducts(
            String name,
            Pageable pageable
    );

    ProductResponse getProductByIdForCart(Long productId);

}
