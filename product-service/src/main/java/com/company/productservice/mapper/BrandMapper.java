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

    public static BrandEntity mapRequestToBrandEntity(BrandRequest brandRequest) {
        return BrandEntity.builder()
                .version(brandRequest.getVersion())
                .name(brandRequest.getName())
                .url(brandRequest.getUrl())
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

}
