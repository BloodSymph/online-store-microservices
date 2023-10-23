package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandAdminDetailResponse;
import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.category.CategoryAdminDetailResponse;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;


@WebMvcTest(controllers = AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryRequest categoryRequest;

    private CategoryAdminResponse categoryAdminResponse;

    private CategoryAdminDetailResponse categoryAdminDetailResponse;

    private BrandAdminResponse brandAdminResponse;

    private BrandAdminDetailResponse brandAdminDetailResponse;

    @BeforeEach
    void setUp() {

        categoryRequest = CategoryRequest.builder()
                .name("Test")
                .url("test")
                .build();

        categoryAdminResponse = CategoryAdminResponse.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        categoryAdminDetailResponse = CategoryAdminDetailResponse.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .brands(new HashSet<>())
                .products(new HashSet<>())
                .build();

        brandAdminResponse = BrandAdminResponse.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        brandAdminDetailResponse = BrandAdminDetailResponse.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .categories(new HashSet<>())
                .products(new HashSet<>())
                .build();
    }

    @Test
    @DisplayName("Get list of all categories test!")
    void AdminController_GetAllCategories_ReturnListOfCategoryAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.getAllCategories(1, 10)
        ).thenReturn(List.of(categoryAdminResponse));

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/api/v1/product-service/admin/categories"
                ).contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber", "1")
                        .param("pageSize", "10")
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Get details of current category test!")
    void AdminController_GetCategoryDetails_ReturnCategoryAdminDetailResponse() throws Exception {

        Mockito.lenient().when(
                adminService.getCategoryDetails("test")
        ).thenReturn(categoryAdminDetailResponse);

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/api/v1/product-service/admin/categories/test"
                        ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryAdminDetailResponse))

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$.name", CoreMatchers.is(categoryAdminDetailResponse.getName()
                                )
                        )
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$.url", CoreMatchers.is(categoryAdminDetailResponse.getUrl()
                                )
                        )
                );

    }

    @Test
    @DisplayName("Search categories test!")
    void AdminController_SearchCategory_ReturnListOfCategoryAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.searchCategories("Test")
        ).thenReturn(List.of(categoryAdminResponse));

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/api/v1/product-service/admin/categories/search"
                        ).contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Test")

        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Create new category test!")
    void AdminController_CreateNewCategory_ReturnCreated() throws Exception {

        Mockito.lenient().when(
                adminService.createCategory(categoryRequest)
        ).thenReturn(categoryAdminResponse);

        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/api/v1/product-service/admin/categories/create"
                ).content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Update category test!")
    void AdminController_UpdateCategory_ReturnUpdated() throws Exception {

        Mockito.lenient().when(
                adminService.updateCategory(categoryRequest, "test")
        ).thenReturn(categoryAdminResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(
                                        "/api/v1/product-service/admin/categories/test/update"
                                ).content(objectMapper.writeValueAsString(categoryRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Delete category test!")
    void AdminController_DeleteCategory_ReturnResponseEntity() throws Exception {

        Mockito.lenient().doNothing().when(adminService).deleteCategory("test");

        mockMvc.perform(
                MockMvcRequestBuilders.delete(
                        "/api/v1/product-service/admin/categories/test/delete"
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Get list of all brands test!")
    void AdminController_GetAllBrands_ReturnListOfBrandAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.getAllBrands(1, 10)
        ).thenReturn(List.of(brandAdminResponse));

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/api/v1/product-service/admin/brands"
                        ).contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber", "1")
                        .param("pageSize", "10")
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Get details of current brand test!")
    void AdminController_GetBrandsDetails_ReturnBrandAdminDetailResponse() throws Exception {

        Mockito.lenient().when(
                adminService.getBrandDetails("test")
        ).thenReturn(brandAdminDetailResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                        "/api/v1/product-service/admin/brands/test"
                                ).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(brandAdminDetailResponse))

                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$.name", CoreMatchers.is(brandAdminDetailResponse.getName()
                                )
                        )
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$.url", CoreMatchers.is(brandAdminDetailResponse.getUrl()
                                )
                        )
                );

    }

    @Test
    @DisplayName("Search brands test!")
    void AdminController_SearchBrands_ReturnListOfBrandAdminResponse() throws Exception {

        Mockito.lenient().when(
                adminService.searchBrands("Test")
        ).thenReturn(List.of(brandAdminResponse));

        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/api/v1/product-service/admin/brands/search"
                        ).contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Test")

        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    // TODO: 23.10.2023 Create brand test
    // TODO: 23.10.2023 Update brand test

    @Test
    @DisplayName("Delete brand test!")
    void AdminController_DeleteBrand_ReturnResponseEntity() throws Exception {

        Mockito.lenient().doNothing().when(adminService).deleteBrand("test");

        mockMvc.perform(
                MockMvcRequestBuilders.delete(
                        "/api/v1/product-service/admin/brands/test/delete"
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}