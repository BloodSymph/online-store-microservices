package com.company.productservice.controller;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.service.ProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/client")
public class ProductInfoController {

    private final ProductInfoService productInfoService;

    @GetMapping("/products/{productUrl}/information")
    @ResponseStatus(HttpStatus.OK)
    public ProductInfoResponse getInformationAboutProduct(
            @PathVariable("productUrl") String productUrl
    ) {
        return productInfoService.getProductInformation(productUrl);
    }

}
