package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandCategoriesResponse;
import com.company.productservice.dto.brand.BrandProductsResponse;
import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/client")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getBrandList() {
        return brandService.getAllBrands();
    }

    @GetMapping("/brands/{brandUrl}/categories")
    @ResponseStatus(HttpStatus.OK)
    public BrandCategoriesResponse getCategoriesBySelectedBrand(
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        return brandService.getCategoriesBySelectedBrand(brandUrl);
    }

    @GetMapping("/brands/{brandUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public BrandProductsResponse getProductsBySelectedBrand(
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        return brandService.getProductsBySelectedBrand(brandUrl);
    }
}
