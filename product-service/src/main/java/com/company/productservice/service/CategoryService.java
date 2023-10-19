package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryResponse> getAllCategories();
}
