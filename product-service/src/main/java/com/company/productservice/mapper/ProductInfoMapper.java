package com.company.productservice.mapper;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.ProductInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductInfoMapper {
    public static ProductInfoResponse mapToProductInfoResponse(ProductInfoEntity productInfo) {
        return ProductInfoResponse.builder()
                .id(productInfo.getId())
                .title(productInfo.getTitle())
                .series(productInfo.getSeries())
                .height(productInfo.getHeight())
                .width(productInfo.getWidth())
                .weight(productInfo.getWeight())
                .os(productInfo.getOs())
                .display(productInfo.getDisplay())
                .resolution(productInfo.getResolution())
                .cpu(productInfo.getCpu())
                .graphicCard(productInfo.getGraphicCard())
                .gpu(productInfo.getGpu())
                .ramType(productInfo.getRamType())
                .ram(productInfo.getRam())
                .memoryType(productInfo.getMemoryType())
                .memory(productInfo.getMemory())
                .build();
    }
}
