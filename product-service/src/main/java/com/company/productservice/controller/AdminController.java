package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminDetailResponse;
import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryAdminResponse> getCategoryList(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return adminService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/categories/{categoryUrl}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryAdminDetailResponse getCategoryDetails(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return adminService.getCategoryDetails(categoryUrl);
    }

    @GetMapping("/categories/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryAdminResponse> searchCategoriesByName(
            @RequestParam(value = "name") String name
    ) {
        return adminService.searchCategories(name);
    }

    @PostMapping("/categories/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryAdminResponse createNewCategory(
            @Valid @RequestBody CategoryRequest categoryRequest
    ) {
        return adminService.createCategory(categoryRequest);
    }

    @PutMapping("/categories/{categoryUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryAdminResponse updateCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl,
            @Valid @RequestBody CategoryRequest categoryRequest
    ) {
        return adminService.updateCategory(categoryRequest, categoryUrl);
    }

    @DeleteMapping("/categories/{categoryUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        adminService.deleteCategory(categoryUrl);
        return new ResponseEntity<>("Category successful deleted!", HttpStatus.OK);
    }

    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandAdminResponse> getBrandList(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return adminService.getAllBrands(pageNumber, pageSize);
    }

    @GetMapping("/brands/{brandUrl}")
    @ResponseStatus(HttpStatus.OK)
    public BrandAdminDetailResponse getBrandDetails(
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        return adminService.getBrandDetails(brandUrl);
    }

    @GetMapping("/brands/search")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandAdminResponse> searchBrands(
            @RequestParam(value = "name") String name
    ) {
        return adminService.searchBrands(name);
    }
    // TODO: 21.10.2023 Create Brand
    // TODO: 21.10.2023 Update Brand

    @DeleteMapping("/brands/{brandUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteBrand(
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        adminService.deleteBrand(brandUrl);
        return new ResponseEntity<>("Brand successful deleted!", HttpStatus.OK);
    }
}
