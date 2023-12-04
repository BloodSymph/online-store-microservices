package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    private CategoryAdminResponse categoryAdminResponse;

    private CategoryRequest categoryRequest;

    private BrandAdminResponse brandAdminResponse;

    private BrandRequest brandRequest;

    private ProductAdminResponse productAdminResponse;

    private ProductRequest productRequest;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        categoryAdminResponse = CategoryAdminResponse.builder()
                .id(1L)
                .version(1L)
                .name("Category")
                .url("category")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryRequest = CategoryRequest.builder()
                .version(1L)
                .name("Category")
                .url("category")
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    void createCategory() {

    }

    @Test
    void updateCategory() {

    }

    @Test
    void createBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }


    @Test
    void createProductInformation() {
    }

    @Test
    void updateProductInformation() {
    }

    @Test
    void deleteProductInformation() {
    }

}