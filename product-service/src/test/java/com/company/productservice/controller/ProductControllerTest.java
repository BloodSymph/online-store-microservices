package com.company.productservice.controller;

import com.company.productservice.dto.product.ProductDetailsResponse;
import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void setUp() {
    }

    @Test
    void getProductDetails() {
    }

    @Test
    void getProductListByCategory() {
    }

    @Test
    void getProductListByBrand() {
    }

}