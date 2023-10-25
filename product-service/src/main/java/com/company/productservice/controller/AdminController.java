package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryAdminResponse> getCategoryList(
           @PageableDefault(
                   sort = "id",
                   direction = Sort.Direction.ASC,
                   page = 0,
                   size = 10
           ) Pageable pageable
    ) {
        return adminService.getAllCategories(pageable);
    }

    @GetMapping("/categories/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryAdminResponse> searchCategoriesByName(
            @RequestParam(value = "name") String name
    ) {
        return adminService.searchCategories(name);
    }

    @GetMapping("/categories/{categoryUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public CategoryAdminResponse getCategoryDetails(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return adminService.getSingleCategory(categoryUrl);
    }

    @GetMapping("/categories/{categoryUrl}/brands")
    @ResponseStatus(HttpStatus.OK)
    public Set<BrandAdminResponse> getBrandsByCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return adminService.getSetOfBrandsByCategory(categoryUrl);
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

    @GetMapping("/brands/{brandUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public BrandAdminResponse getBrandDetails(
            @PathVariable("brandUrl") String brandUrl
    ) {
        return adminService.getSingleBrand(brandUrl);
    }

    @GetMapping("/brands/{brandUrl}/categories")
    @ResponseStatus(HttpStatus.OK)
    public Set<CategoryAdminResponse> getCategoriesByBrand(
            @PathVariable("brandUrl") String brandUrl
    ) {
        return adminService.getSetOfCategoriesByBrand(brandUrl);
    }

    @GetMapping("/brands/search")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandAdminResponse> searchBrands(
            @RequestParam(value = "name") String name
    ) {
        return adminService.searchBrands(name);
    }

    @PostMapping("/categories/{categoryUrl}/brands/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandAdminResponse createBrand(
            @RequestBody BrandRequest brandRequest,
            @PathVariable(value = "categoryUrl") String categoryUrl
    ) {
        return adminService.createBrand(brandRequest, categoryUrl);
    }

    @PutMapping("/brands/{brandUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandAdminResponse updateBrand(
            @RequestBody BrandRequest brandRequest,
            @PathVariable("brandUrl") String brandUrl
    ) {
        return adminService.updateBrand(brandRequest, brandUrl);
    }

    @DeleteMapping("/brands/{brandUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteBrand(
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        adminService.deleteBrand(brandUrl);
        return new ResponseEntity<>("Brand successful deleted!", HttpStatus.OK);
    }
}
