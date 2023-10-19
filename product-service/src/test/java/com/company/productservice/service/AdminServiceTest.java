package com.company.productservice.service;

import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.implementation.AdminServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private AdminServiceImplementation adminServiceImplementation;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void getCategoryDetails() {
    }

    @Test
    void searchCategories() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
}