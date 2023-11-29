package com.company.productservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @BeforeEach
    void setUp() {
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