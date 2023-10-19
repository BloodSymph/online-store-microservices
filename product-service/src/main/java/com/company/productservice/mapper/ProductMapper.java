package com.company.productservice.mapper;

import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductDetailAdminResponse;
import com.company.productservice.dto.product.ProductDetailResponse;
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
    public static ProductDetailResponse mapToProductDetailResponse(ProductEntity product) {
        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .productInfo(
                        ProductInfoMapper.mapToProductInfoResponse(
                                product.getProductInfo()
                        )
                )
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
    public static ProductDetailAdminResponse mapToProductDetailAdminResponse(ProductEntity product) {
        return ProductDetailAdminResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .url(product.getUrl())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .category(
                        CategoryMapper.mapToCategoryResponse(
                                product.getCategory()
                        )
                )
                .brand(
                        BrandMapper.mapToBrandResponse(
                                product.getBrand()
                        )
                )
                .productInfo(
                        ProductInfoMapper.mapToProductInfoResponse(
                                product.getProductInfo()
                        )
                )
                .build();
    }
}
