package com.company.productservice.service;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductInfoService {

    ProductInfoResponse getProductInformation(String productUrl);

}
