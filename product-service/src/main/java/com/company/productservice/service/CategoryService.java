package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryBrandsResponse;
import com.company.productservice.dto.category.CategoryProductsResponse;
import com.company.productservice.dto.category.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryBrandsResponse getBrandsBySelectedCategory(String categoryUrl);
    CategoryProductsResponse getProductsBySelectedCategory(String categoryUrl);
}
