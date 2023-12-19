package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminService adminService;

    private CategoryAdminResponse categoryAdminResponse;

    private CategoryRequest categoryRequest;

    private BrandAdminResponse brandAdminResponse;

    private BrandRequest brandRequest;

    private ProductAdminResponse productAdminResponse;

    private ProductRequest productRequest;


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

        brandAdminResponse = BrandAdminResponse.builder()
                .id(1L)
                .version(1L)
                .name("Brand")
                .url("brand")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        brandRequest = BrandRequest.builder()
                .version(1L)
                .name("Brand")
                .url("brand")
                .build();

        productAdminResponse = ProductAdminResponse.builder()
                .id(1L)
                .version(1L)
                .name("Product")
                .url("product")
                .price("100$")
                .photoUrl("https://photo.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRequest = ProductRequest.builder()
                .version(1L)
                .name("Product")
                .url("product")
                .price("100$")
                .photoUrl("https://photo.com")
                .build();

    }

    @Test
    @DisplayName("Create category test!")
    void AdminController_CreateCategory_ReturnCategoryAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.createCategory(categoryRequest)
        ).thenReturn(categoryAdminResponse);

        mockMvc.perform(
                post(
                        "/api/v1/product-service/admin/categories/create"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(categoryRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }

    @Test
    @DisplayName("Update category test!")
    void AdminController_UpdateCategory_ReturnCategoryAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.updateCategory(
                        categoryRequest,
                        "category"
                )
        ).thenReturn(categoryAdminResponse);

        mockMvc.perform(
                put(
                        "/api/v1/product-service/admin/categories/category/update"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(categoryRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }

    @Test
    @DisplayName("Create brand test!")
    void AdminController_CreateBrand_ReturnBrandAdminResponse() throws Exception {

        Mockito.lenient().when(
            adminService.createBrand(
                  brandRequest,
                  "category"
            )
        ).thenReturn(brandAdminResponse);

        mockMvc.perform(
                post(
                        "/api/v1/product-service/admin/categories/category/brands/create"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(brandRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }

    @Test
    @DisplayName("Update brand test!")
    void AdminController_UpdateBrand_ReturnBrandAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.updateBrand(
                        brandRequest,
                        "brand"
                )
        ).thenReturn(brandAdminResponse);

        mockMvc.perform(
                put(
                        "/api/v1/product-service/admin/brands/brand/update"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(brandRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }

    @Test
    @DisplayName("Create product test!")
    void AdminController_CreateProduct_ReturnProductAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.createProduct(
                        productRequest,
                        "category",
                        "brand"
                )
        ).thenReturn(productAdminResponse);

        mockMvc.perform(
                post(
                        "/api/v1/product-service/admin/categories/category/brands/brand/products/create"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(productRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }

    @Test
    @DisplayName("Update product test!")
    void AdminController_UpdateProduct_ReturnProductAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.updateProduct(
                        productRequest,
                        "product"
                )
        ).thenReturn(productAdminResponse);

        mockMvc.perform(
                put(
                        "/api/v1/product-service/admin/products/product/update"
                ).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(productRequest)
                )
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andDo(print());

    }


}