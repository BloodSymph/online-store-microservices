package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.implementation.CategoryServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import static org.junit.Assert.assertThrows;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImplementation categoryServiceImplementation;

    private CategoryEntity category;

    private Pageable pageable;


    @BeforeEach
    void setUp() {

        category = CategoryEntity.builder()
                .version(1L)
                .id(1L)
                .name("Category")
                .url("category")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .brands(new HashSet<>())
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get all categories test!")
    void CategoryService_GetAllCategories_ReturnPageOfCategoriesResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.findAll(
                        pageable
                )
        ).thenReturn(categoryEntityPage);

        Page<CategoryResponse> categoryResponses = categoryServiceImplementation
                .getAllCategories(
                        pageable
                );

        Assertions.assertThat(categoryResponses).isNotNull();

    }

    @Test
    @DisplayName("Get category by url test!")
    void CategoryService_GetSingleCategory_ReturnCategoryResponse() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "category"
                )
        ).thenReturn(Optional.of(category));

        CategoryResponse category = categoryServiceImplementation
                .getSingleCategory(
                        "category"
                );

        Assertions.assertThat(category).isNotNull();

    }

    @Test
    @DisplayName("Get category by url throw exception test!")
    void CategoryService_GetSingleCategory_ThrowError() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "category"
                )
        ).thenReturn(Optional.of(category));

        assertThrows(
                CategoryNotFoundException.class,
                () -> categoryServiceImplementation.getSingleCategory(
                        "error"
                )
        );

    }

    @Test
    @DisplayName("Search category by name test!")
    void CategoryService_SearchCategory_ReturnPageOfCategoriesResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.searchByNameIgnoreCase(
                        "Category", pageable
                )
        ).thenReturn(categoryEntityPage);

        Page<CategoryResponse> categoryResponses = categoryServiceImplementation
                .searchCategories(
                        "Category", pageable
                );

        Assertions.assertThat(categoryResponses).isNotNull();

    }

    @Test
    @DisplayName("Get categories by brand test!")
    void CategoryService_GetCategoryByBrand_ReturnPageOfCategoriesResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.findCategoryEntitiesByBrands_UrlIgnoreCase(
                        "brand", pageable
                )
        ).thenReturn(categoryEntityPage);

        Page<CategoryResponse> categoryResponses = categoryServiceImplementation
                .getCategoriesByBrand(
                        "brand", pageable
                );

        Assertions.assertThat(categoryResponses).isNotNull();

    }

}