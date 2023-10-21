package com.company.productservice.service;

import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.implementation.AdminServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private AdminServiceImplementation adminServiceImplementation;

    private CategoryEntity category;
    private CategoryRequest categoryRequest;
    private BrandEntity brand;

    @BeforeEach
    void setUp() {

        category = CategoryEntity.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .brands(new HashSet<>())
                .products(new HashSet<>())
                .build();

        categoryRequest = CategoryRequest.builder()
                .name("Test")
                .url("test")
                .build();

    }

    @Test
    @DisplayName("Get all categories list and pagination test!")
    void AdminService_GetCategoryList_ReturnCategoryAdminResponse() {

        Page<CategoryEntity> categoryEntityPage = Mockito.mock(Page.class);

        Mockito.lenient().when(
                categoryRepository.findAll(
                        Mockito.any(Pageable.class)
                )
        ).thenReturn(categoryEntityPage);

        List<CategoryAdminResponse> categoryAdminResponses = adminServiceImplementation
                .getAllCategories(1, 10);

        Assertions.assertThat(categoryAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get categories details by category url test!")
    void AdminService_GetCategoryDetail_ReturnCategoryAdminDetailResponse() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "test"
                )
        ).thenReturn(Optional.of(category));

        CategoryAdminDetailResponse categoryAdminDetailResponse = adminServiceImplementation
                .getCategoryDetails("test");

        Assertions.assertThat(categoryAdminDetailResponse).isNotNull();

    }

    @Test
    @DisplayName("Search categories by name test!")
    void AdminService_SearchCategory_ReturnListOfCategoryAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.searchByNameIgnoreCase(
                        "Test"
                )
        ).thenReturn(List.of(category));

        List<CategoryAdminResponse> categoryAdminResponses = adminServiceImplementation
                .searchCategories("Test");

        Assertions.assertThat(categoryAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Create category test!")
    void AdminService_CreateCategory_ReturnCategoryAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.save(Mockito.any(CategoryEntity.class))
        ).thenReturn(category);

        CategoryRequest categoryAdminResponse = adminServiceImplementation
                .createCategory(categoryRequest);

        Assertions.assertThat(categoryAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Update category test!")
    void AdminService_UpdateCategory_ReturnCategoryAdminResponse() {

        Mockito.lenient().when(
              categoryRepository.findByUrlIgnoreCase("test")
        ).thenReturn(Optional.of(category));

        Mockito.lenient().when(
                categoryRepository.save(category)
        ).thenReturn(category);

        CategoryRequest categoryAdminResponse = adminServiceImplementation
                .updateCategory(categoryRequest, "test");

        Assertions.assertThat(categoryAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Delete category exception test!")
    void AdminService_DeleteCategory_ReturnCategoryNotFoundException() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase("test1")
        ).thenReturn(Optional.ofNullable(category));

        assertThrows(
                CategoryNotFoundException.class,
                () -> adminServiceImplementation.deleteCategory("test1")
        );

    }
}