package com.company.productservice.service.implementation;

import com.company.productservice.dto.category.CategoryBrandsResponse;
import com.company.productservice.dto.category.CategoryProductsResponse;
import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.CategoryMapper.mapToCategoryBrandsResponse;
import static com.company.productservice.mapper.CategoryMapper.mapToCategoryProductsResponse;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(CategoryMapper::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryBrandsResponse getBrandsBySelectedCategory(String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Can not get brands by selected category!")
                );
        return mapToCategoryBrandsResponse(category);
    }

    @Override
    public CategoryProductsResponse getProductsBySelectedCategory(String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Can not get products by selected category!")
                );
        return mapToCategoryProductsResponse(category);
    }
}
