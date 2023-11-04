package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.dto.product_info.ProductInfoRequest;
import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.ProductInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AdminService {

    Page<CategoryAdminResponse> getAllCategories(Pageable pageable);

    Page<CategoryAdminResponse> searchCategories(String name, Pageable pageable);

    CategoryAdminResponse getSingleCategory(String categoryUrl);

    Page<CategoryAdminResponse> getSetOfCategoriesByBrand(String brandUrl, Pageable pageable);

    CategoryAdminResponse createCategory(CategoryRequest categoryRequest);

    CategoryAdminResponse updateCategory(
            CategoryRequest categoryRequest, String categoryUrl
    );

    void deleteCategory(String categoryUrl);

    Page<BrandAdminResponse> getAllBrands(Pageable pageable);

    BrandAdminResponse getSingleBrand(String brandUrl);

    Page<BrandAdminResponse> getSetOfBrandsByCategory(String categoryUrl, Pageable pageable);

    Page<BrandAdminResponse> searchBrands(String name, Pageable pageable);

    BrandAdminResponse createBrand(
            BrandRequest brandRequest, String categoryUrl
    );

    BrandAdminResponse updateBrand(
            BrandRequest brandRequest, String brandUrl
    );

    void deleteBrand(String brandUrl);

    Page<ProductAdminResponse> getAllProducts(Pageable pageable);

    ProductAdminResponse getSingleProduct(String productUrl);

    Page<ProductAdminResponse> searchProducts(
            String name,
            Pageable pageable
    );

    Page<ProductAdminResponse> getProductsByCategory(
            String categoryUrl, Pageable pageable
    );

    Page<ProductAdminResponse> getProductsByBrand(
            String brandUrl, Pageable pageable
    );

    ProductAdminResponse createProduct(
            ProductRequest productRequest,
            String categoryUrl,
            String brandUrl
    );

    ProductAdminResponse updateProduct(
            ProductRequest productRequest, String productUrl
    );

    void deleteProduct(String productUrl);

    ProductInfoResponse getProductInfo(String productUrl);

    ProductInfoResponse createProductDescription(
            ProductInfoRequest productInfoRequest, String productUrl
    );

    ProductInfoResponse updateProductDescription(
            ProductInfoRequest productInfoRequest, String productUrl
    );

    void deleteProductDescription(String productUrl);

}
