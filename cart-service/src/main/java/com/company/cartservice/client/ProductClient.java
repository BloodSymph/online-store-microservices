package com.company.cartservice.client;

import com.company.cartservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8081/api/v1/product-service/client"
)
public interface ProductClient {

    @GetMapping("/products/{productId}/get")
    ProductResponse getProductForAddToTheCart(
            @PathVariable(name = "productId") Long productId
    );

}
