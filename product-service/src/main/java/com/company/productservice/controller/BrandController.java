package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.service.BrandService;
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
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandResponse> getBrandsList(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable) {

        return brandService.getAllBrands(pageable);

    }

    @GetMapping("/brands/{brandUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public BrandResponse getBrandDetails(
            @PathVariable("brandUrl") String brandUrl) {

        return brandService.getCurrentBrand(brandUrl);

    }

    @GetMapping("/brands/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandResponse> searchBrands(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable) {

        return brandService.searchBrands(
                name, pageable
        );

    }

    @GetMapping("/categories/{categoryUrl}/brands")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandResponse> getBrandsByCategory(
            @PathVariable("categoryUrl") String categoryUrl,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable) {

        return brandService.getBrandsByCategory(
                categoryUrl, pageable
        );

    }

}
