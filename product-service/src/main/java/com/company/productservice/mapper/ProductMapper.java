package com.company.productservice.mapper;

import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductResponse mapToProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .build();
    }
    public static ProductAdminResponse mapToProductAdminResponse(ProductEntity product) {
        return ProductAdminResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .url(product.getUrl())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
