package com.company.productservice.service.implementation;

import com.company.productservice.dto.brand.BrandAdminDetailResponse;
import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.mapper.BrandMapper;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.BrandMapper.mapToBrandAdminDetailResponse;
import static com.company.productservice.mapper.CategoryMapper.*;
import static com.company.productservice.utils.SlugGenerator.toSlug;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

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
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryRequest.getName());
        category.setUrl(categoryRequest.getUrl());
        CategoryEntity createCategory = categoryRepository.save(category);
        return mapCategoryToRequest(createCategory);
    }

    @Override
    public CategoryRequest updateCategory(CategoryRequest categoryRequest, String categoryUrl) {
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
        return mapCategoryToRequest(updatedCategory);
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

    @Override
    public List<BrandAdminResponse> getAllBrands(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);
        List<BrandEntity> brands = brandEntityPage.getContent();
        return brands.stream()
                .map(BrandMapper::mapToBrandAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandAdminDetailResponse getBrandDetails(String brandUrl) {
        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not find brand by url: " + brandUrl
                        )
                );
        return mapToBrandAdminDetailResponse(brand);
    }

    @Override
    public List<BrandAdminResponse> searchBrands(String name) {
        List<BrandEntity> brands = brandRepository.searchByNameIgnoreCase(name);
        return brands.stream()
                .map(BrandMapper::mapToBrandAdminResponse)
                .collect(Collectors.toList());
    }
    // TODO: 21.10.2023 Create Brand
    // TODO: 21.10.2023 Update Brand

    @Override
    public void deleteBrand(String brandUrl) {
        if (!brandRepository.existsByUrlIgnoreCase(brandUrl)) {
            throw new BrandNotFoundException(
                    "Can not delete brand by current url: " + brandUrl
            );
        }
        brandRepository.deleteByUrlIgnoreCase(brandUrl);
    }
}
