package com.company.productservice.mapper;

import com.company.productservice.dto.category.*;
import com.company.productservice.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public static CategoryResponse mapToCategoryResponse(CategoryEntity category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
    public static CategoryAdminResponse mapToCategoryAdminResponse(CategoryEntity category) {
        return CategoryAdminResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .url(category.getUrl())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
    public static CategoryAdminDetailResponse mapToCategoryAdminDetailResponse(CategoryEntity category) {
        return CategoryAdminDetailResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .url(category.getUrl())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .brands(
                        category.getBrands()
                                .stream()
                                .map(BrandMapper::mapToBrandResponse)
                                .collect(Collectors.toSet())
                )
                .products(
                        category.getProducts()
                                .stream()
                                .map(ProductMapper::mapToProductResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    public static CategoryEntity mapRequestToCategory(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .url(categoryRequest.getUrl())
                .build();
    }
    public static CategoryProductsResponse mapToCategoryProductsResponse(CategoryEntity category) {
        return CategoryProductsResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .products(
                        category.getProducts()
                                .stream()
                                .map(ProductMapper::mapToProductResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    public static CategoryBrandsResponse mapToCategoryBrandsResponse(CategoryEntity category) {
        return CategoryBrandsResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .brands(
                        category.getBrands()
                                .stream()
                                .map(BrandMapper::mapToBrandResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}

