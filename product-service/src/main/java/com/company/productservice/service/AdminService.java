package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {
    List<CategoryAdminResponse> getAllCategories(int pageNumber, int pageSize);
    CategoryAdminDetailResponse getCategoryDetails(String categoryUrl);
    List<CategoryAdminResponse> searchCategories(String name);
    CategoryAdminResponse createCategory(CategoryRequest categoryRequest);
    CategoryAdminResponse updateCategory(CategoryRequest categoryRequest, String categoryUrl);
    void deleteCategory(String categoryUrl);

}
