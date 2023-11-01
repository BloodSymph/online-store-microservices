package com.company.productservice.controller;

import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/client")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getProductList(
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/products/{productUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductDetails(
            @PathVariable("productUrl") String productUrl
    ) {
        return productService.getSingleProduct(productUrl);
    }

    @GetMapping("/categories/{categoryUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getProductListByCategory(
            @PathVariable String categoryUrl,
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return productService.getProductsByCategory(
                categoryUrl, pageable
        );
    }

    @GetMapping("/brands/{brandUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getProductListByBrand(
            @PathVariable("brandUrl") String brandUrl,
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return productService.getProductsByBrand(
                brandUrl, pageable
        );
    }

    @GetMapping("/products/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> searchProducts(
            @RequestParam(
                    value = "productName", required = false
            ) String productName,
            @RequestParam(
                    value = "categoryName", required = false
            ) String categoryName,
            @RequestParam(
                    value = "brandName", required = false
            ) String brandName,
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return productService.searchProducts(
                productName,
                categoryName,
                brandName,
                pageable
        );
    }

}
