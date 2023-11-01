package com.company.productservice.controller;

import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> getCategoriesList(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/categories/{categoryUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryDetails(
            @PathVariable("categoryUrl") String categoryUrl
    ) {
        return categoryService.getSingleCategory(categoryUrl);
    }

    @GetMapping("/categories/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> searchCategories(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return categoryService.searchCategories(
                name, pageable
        );
    }

    @GetMapping("/brands/{brandUrl}/categories")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> getCategoriesListByBrand(
            @PathVariable("brandUrl") String brandUrl,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return categoryService.getCategoriesByBrand(
                brandUrl, pageable
        );
    }

}
