package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AdminService {
    List<CategoryAdminResponse> getAllCategories(int pageNumber, int pageSize);
    List<CategoryAdminResponse> searchCategories(String name);
    CategoryAdminResponse getSingleCategory(String categoryUrl);
    Set<CategoryAdminResponse> getSetOfCategoriesByBrand(String brandUrl);
    CategoryAdminResponse createCategory(CategoryRequest categoryRequest);
    CategoryAdminResponse updateCategory(CategoryRequest categoryRequest, String categoryUrl);
    void deleteCategory(String categoryUrl);
    List<BrandAdminResponse> getAllBrands(int pageNumber, int pageSize);
    BrandAdminResponse getSingleBrand(String brandUrl);
    Set<BrandAdminResponse> getSetOfBrandsByCategory(String categoryUrl);
    List<BrandAdminResponse> searchBrands(String name);
    BrandAdminResponse createBrand(BrandRequest brandRequest, String categoryUrl);
    BrandAdminResponse updateBrand(BrandRequest brandRequest, String brandUrl);
    void deleteBrand(String brandUrl);
}
