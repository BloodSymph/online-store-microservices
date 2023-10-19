package com.company.productservice.service.implementation;

import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.CategoryMapper.*;
import static com.company.productservice.utils.SlugGenerator.toSlug;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryAdminResponse> getAllCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryEntity> categoryEntityList = categoryPage.getContent();
        return categoryEntityList.stream()
                .map(CategoryMapper::mapToCategoryAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAdminDetailResponse getCategoryDetails(String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by url: " + categoryUrl
                        )
                );
        return mapToCategoryAdminDetailResponse(category);
    }

    @Override
    public List<CategoryAdminResponse> searchCategories(String name) {
        List<CategoryEntity> categories = categoryRepository.searchByNameIgnoreCase(name);
        return categories.stream()
                .map(CategoryMapper::mapToCategoryAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAdminResponse createCategory(CategoryRequest categoryRequest) {
        CategoryEntity category = mapRequestToCategory(categoryRequest);
        category.setUrl(toSlug(categoryRequest.getName()));
        CategoryEntity createdCategory = categoryRepository.save(category);
        return mapToCategoryAdminResponse(createdCategory);
    }

    @Override
    public CategoryAdminResponse updateCategory(CategoryRequest categoryRequest, String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not update category with current url: " + categoryUrl
                        )
                );
        category.setName(categoryRequest.getName());
        category.setUrl(toSlug(categoryRequest.getName()));
        CategoryEntity updatedCategory = categoryRepository.save(category);
        return mapToCategoryAdminResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryUrl) {
        if (!categoryRepository.existsByUrlIgnoreCase(categoryUrl)) {
            throw new CategoryNotFoundException(
                    "Can not delete category by current url: " + categoryUrl
            );
        }
        categoryRepository.deleteByUrlIgnoreCase(categoryUrl);
    }
}
