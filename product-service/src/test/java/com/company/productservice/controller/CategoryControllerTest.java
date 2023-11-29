package com.company.productservice.controller;

import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.service.CategoryService;
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
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private CategoryResponse categoryResponse;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        categoryResponse = CategoryResponse.builder()
                .id(1L)
                .name("Category")
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get categories by brand test!")
    void CategoryController_GetCategoriesByBrands_ReturnPageCategoriesResponse() throws Exception {

        List<CategoryResponse> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(categoryResponse);

        Page<CategoryResponse> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryService.getCategoriesByBrand(
                        "brand", pageable
                )
        ).thenReturn(categoryEntityPage);

        mockMvc.perform(
                get(
                        "/api/v1/product-service/client/brands/brand/categories"
                )
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .param("sort", "name")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andDo(print());

        verify(
                categoryService,
                times(1)
        ).getCategoriesByBrand(
                "brand" , pageable
        );

    }

}