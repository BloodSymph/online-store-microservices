package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
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
    public Page<CategoryAdminResponse> searchCategoriesByName(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.searchCategories(name, pageable);
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
    public Page<BrandAdminResponse> getBrandsByCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.getSetOfBrandsByCategory(categoryUrl, pageable);
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
    public Page<BrandAdminResponse> getBrandList(
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.getAllBrands(pageable);
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
    public Page<CategoryAdminResponse> getCategoriesByBrand(
            @PathVariable("brandUrl") String brandUrl,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.getSetOfCategoriesByBrand(brandUrl, pageable);
    }

    @GetMapping("/brands/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandAdminResponse> searchBrands(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.searchBrands(name, pageable);
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

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> getProductsList(
           @PageableDefault(
                   sort = "id",
                   direction = Sort.Direction.ASC,
                   page = 0,
                   size = 10
           ) Pageable pageable
    ) {
        return adminService.getAllProducts(pageable);
    }

    @GetMapping("/products/{productUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public ProductAdminResponse getProductsDetails(
            @PathVariable("productUrl") String productUrl
    ) {
        return adminService.getSingleProduct(productUrl);
    }

    @GetMapping("/products/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> searchProducts(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "brandName", required = false) String brandName,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.searchProducts(
                productName, categoryName, brandName, pageable
        );
    }

    @GetMapping("/categories/{categoryUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> getProductsByCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.getProductsByCategory(categoryUrl, pageable);
    }

    @GetMapping("/brands/{brandUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> getProductsByBrand(
            @PathVariable(value = "brandUrl") String brandUrl,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable
    ) {
        return adminService.getProductsByBrand(brandUrl, pageable);
    }

    @PostMapping(
            "/categories/{categoryUrl}/brands/{brandUrl}/products/create"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAdminResponse createProduct(
            @Valid @RequestBody ProductRequest productRequest,
            @PathVariable(value = "categoryUrl") String categoryUrl,
            @PathVariable(value = "brandUrl") String brandUrl
    ) {
        return adminService.createProduct(
                productRequest, categoryUrl, brandUrl
        );
    }

    @PutMapping("/products/{productUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAdminResponse updateProduct(
            @Valid @RequestBody ProductRequest productRequest,
            @PathVariable(value = "productUrl") String productUrl
    ) {
        return adminService.updateProduct(productRequest, productUrl);
    }

    @DeleteMapping("/products/{productUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduct(
            @PathVariable(value = "productUrl") String productUrl
    ) {
        adminService.deleteProduct(productUrl);
        return new ResponseEntity<>("Product successful deleted!", HttpStatus.OK);
    }



}
