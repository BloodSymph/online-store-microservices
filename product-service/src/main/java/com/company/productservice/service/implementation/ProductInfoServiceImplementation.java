package com.company.productservice.service.implementation;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.repository.ProductInfoRepository;
import com.company.productservice.service.ProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.company.productservice.mapper.ProductInfoMapper.mapToProductInfoResponse;

@Service
@RequiredArgsConstructor
public class ProductInfoServiceImplementation implements ProductInfoService {

    private final ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfoResponse getProductInformation(String productUrl) {
        ProductInfoEntity productInfo = productInfoRepository
                .findByProductUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product info by current product url: " + productUrl
                        )
                );
        return mapToProductInfoResponse(productInfo);
    }

}
