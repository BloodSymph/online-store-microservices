package com.company.productservice.service.implementation;

import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.company.productservice.mapper.CategoryMapper.mapToCategoryResponse;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryResponse> getAllCategories(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(CategoryMapper::mapToCategoryResponse);
    }

    @Override
    public CategoryResponse getSingleCategory(String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not get category by current url: " + categoryUrl
                        )
                );
        return mapToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> searchCategories(
            String name, Pageable pageable
    ) {
        return categoryRepository
                .searchByNameIgnoreCase(name, pageable)
                .map(CategoryMapper::mapToCategoryResponse);
    }

    @Override
    public Page<CategoryResponse> getCategoriesByBrand(
            String brandUrl, Pageable pageable
    ) {
        return categoryRepository
                .findCategoryEntitiesByBrands_UrlIgnoreCase(
                        brandUrl, pageable
                ).map(CategoryMapper::mapToCategoryResponse);
    }

}
