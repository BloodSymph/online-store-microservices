package com.company.productservice.controller;

import com.company.productservice.dto.category.CategoryBrandsResponse;
import com.company.productservice.dto.category.CategoryProductsResponse;
import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/client")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getListCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{categoryUrl}/brands")
    @ResponseStatus(HttpStatus.OK)
    public CategoryBrandsResponse getBrandsBySelectedCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return categoryService.getBrandsBySelectedCategory(categoryUrl);
    }

    @GetMapping("/categories/{categoryUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public CategoryProductsResponse getProductsBySelectedCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return categoryService.getProductsBySelectedCategory(categoryUrl);
    }

}
