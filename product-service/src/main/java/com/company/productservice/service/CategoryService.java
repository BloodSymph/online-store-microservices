package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    Page<CategoryResponse> getAllCategories(Pageable pageable);

    CategoryResponse getSingleCategory(String categoryUrl);

    Page<CategoryResponse> searchCategories(
            String name, Pageable pageable
    );

    Page<CategoryResponse> getCategoriesByBrand(
            String brandUrl, Pageable pageable
    );

}
