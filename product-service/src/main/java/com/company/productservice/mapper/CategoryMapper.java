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

    public static CategoryEntity mapRequestToCategoryEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .url(categoryRequest.getUrl())
                .build();
    }

}

