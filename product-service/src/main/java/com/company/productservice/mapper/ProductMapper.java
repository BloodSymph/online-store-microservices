package com.company.productservice.mapper;

import com.company.productservice.dto.product.*;
import com.company.productservice.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static com.company.productservice.mapper.ProductInfoMapper.mapToProductInfoResponse;

@Component
public class ProductMapper {

    public static ProductResponse mapToProductResponse(
            ProductEntity product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .build();

    }

    public static ProductDetailsResponse mapToProductDetailsResponse(
            ProductEntity product) {

        return ProductDetailsResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photoUrl(product.getPhotoUrl())
                .productInfoResponse(
                        mapToProductInfoResponse(
                                product.getProductInfo()
                        )
                )
                .build();

    }

    public static ProductDetailsAdminResponse mapToProductDetailsAdminResponse(
            ProductEntity product) {

            return ProductDetailsAdminResponse.builder()
                    .version(product.getVersion())
                    .id(product.getId())
                    .name(product.getName())
                    .url(product.getUrl())
                    .price(product.getPrice())
                    .photoUrl(product.getPhotoUrl())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .productInfoResponse(
                            mapToProductInfoResponse(
                                    product.getProductInfo()
                            )
                    )
                    .build();

    }

    public static ProductEntity mapRequestToProductEntity(
            ProductRequest productRequest) {

        return ProductEntity.builder()
                .version(productRequest.getVersion())
                .name(productRequest.getName())
                .url(productRequest.getUrl())
                .price(productRequest.getPrice())
                .photoUrl(productRequest.getPhotoUrl())
                .build();

    }

    public static ProductAdminResponse mapToProductAdminResponse(
            ProductEntity product) {

        return ProductAdminResponse.builder()
                .version(product.getVersion())
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
