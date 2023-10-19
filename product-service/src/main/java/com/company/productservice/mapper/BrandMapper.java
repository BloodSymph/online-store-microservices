package com.company.productservice.mapper;

import com.company.productservice.dto.brand.*;
import com.company.productservice.entity.BrandEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BrandMapper {
    public static BrandResponse mapToBrandResponse(BrandEntity brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
    public static BrandCategoriesResponse mapToBrandCategoriesResponse(BrandEntity brand) {
        return BrandCategoriesResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .categories(
                        brand.getCategories()
                                .stream()
                                .map(CategoryMapper::mapToCategoryResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    public static BrandProductsResponse mapToBrandProductsResponse(BrandEntity brand) {
        return BrandProductsResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .products(
                        brand.getProducts()
                                .stream()
                                .map(ProductMapper::mapToProductResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    public static BrandAdminResponse mapToBrandAdminResponse(BrandEntity brand) {
        return BrandAdminResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .url(brand.getUrl())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }
    public static BrandAdminDetailResponse mapToBrandAdminDetailResponse(BrandEntity brand) {
        return BrandAdminDetailResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .url(brand.getUrl())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .categories(
                        brand.getCategories()
                                .stream()
                                .map(CategoryMapper::mapToCategoryResponse)
                                .collect(Collectors.toSet())
                )
                .products(
                        brand.getProducts()
                                .stream()
                                .map(ProductMapper::mapToProductResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
