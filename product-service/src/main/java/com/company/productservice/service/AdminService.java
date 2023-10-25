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

    List<CategoryAdminResponse> searchCategories(String name);

    CategoryAdminResponse getSingleCategory(String categoryUrl);

    Set<CategoryAdminResponse> getSetOfCategoriesByBrand(String brandUrl);

    CategoryAdminResponse createCategory(CategoryRequest categoryRequest);

    CategoryAdminResponse updateCategory(
            CategoryRequest categoryRequest, String categoryUrl
    );

    void deleteCategory(String categoryUrl);

    List<BrandAdminResponse> getAllBrands(int pageNumber, int pageSize);

    BrandAdminResponse getSingleBrand(String brandUrl);

    Set<BrandAdminResponse> getSetOfBrandsByCategory(String categoryUrl);

    List<BrandAdminResponse> searchBrands(String name);

    BrandAdminResponse createBrand(
            BrandRequest brandRequest, String categoryUrl
    );

    BrandAdminResponse updateBrand(
            BrandRequest brandRequest, String brandUrl
    );

    void deleteBrand(String brandUrl);

    List<ProductAdminResponse> getAllProducts(int pageNumber, int pageSize, String sort);

    ProductAdminResponse getSingleProduct(String productUrl);

    List<ProductAdminResponse> searchProducts(
            String productName, String categoryName, String brandName
    );

    List<ProductAdminResponse> getProductsByCategory(String categoryUrl);

    List<ProductAdminResponse> getProductsByBrand(String brandUrl);

    ProductAdminResponse createProduct(
            ProductRequest productRequest, String categoryUrl, String brandUrl
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
