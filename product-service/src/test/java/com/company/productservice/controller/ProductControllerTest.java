package com.company.productservice.controller;

import com.company.productservice.dto.product.ProductDetailsResponse;
import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.service.ProductService;
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


@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductResponse productResponse;

    private ProductDetailsResponse productDetailsResponse;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        productResponse = ProductResponse.builder()
                .id(1L)
                .name("Product")
                .price("100$")
                .photoUrl("https://photo.com")
                .build();

        productDetailsResponse = ProductDetailsResponse.builder()
                .id(1L)
                .name("Product")
                .price("100$")
                .photoUrl("https://photo.com")
                .productInfoResponse(new ProductInfoResponse())
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get product details test!")
    void ProductController_GetProductDetails_ReturnProductDetailsResponse() throws Exception {

        Mockito.lenient().when(
                productService.getSingleProductDetails("product")
        ).thenReturn(productDetailsResponse);

        mockMvc.perform(
                get(
                        "/api/v1/product-service/client/products/product/details"
                )
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andDo(print());

        verify(
                productService,
                times(1)
        ).getSingleProductDetails(
                "product"
        );

    }

    @Test
    @DisplayName("Get products by category test!")
    void ProductService_GetProductsByCategory_ReturnPageProductsResponse() throws Exception {

        List<ProductResponse> productResponseList = new ArrayList<>();
        productResponseList.add(productResponse);

        Page<ProductResponse> productResponsePage = new PageImpl<>(productResponseList);

        Mockito.lenient().when(
                productService.getProductsByCategory(
                        "category", pageable
                )
        ).thenReturn(productResponsePage);

        mockMvc.perform(
                get(
                        "/api/v1/product-service/client/categories/category/products"
                )
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .param("sort", "name")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andDo(print());

        verify(
                productService,
                times(1)
        ).getProductsByCategory("category", pageable);

    }

    @Test
    @DisplayName("Get products by brand test!")
    void ProductService_GetProductsByBrand_ReturnPageProductsResponse() throws Exception {

        List<ProductResponse> productResponseList = new ArrayList<>();
        productResponseList.add(productResponse);

        Page<ProductResponse> productResponsePage = new PageImpl<>(productResponseList);

        Mockito.lenient().when(
                productService.getProductsByBrand(
                        "brand", pageable
                )
        ).thenReturn(productResponsePage);

        mockMvc.perform(
                get(
                        "/api/v1/product-service/client/brands/brand/products"
                )
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .param("sort", "name")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andDo(print());

    }

}