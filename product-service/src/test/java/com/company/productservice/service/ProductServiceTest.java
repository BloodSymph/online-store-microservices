package com.company.productservice.service;

import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.implementation.ProductServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImplementation productServiceImplementation;

    private ProductEntity product;

    private Pageable pageable;


    @BeforeEach
    void setUp() {

        product = ProductEntity.builder()
                .version(1L)
                .id(1L)
                .name("Product")
                .url("product")
                .price("100")
                .photoUrl("https://photo")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .category(new CategoryEntity())
                .brand(new BrandEntity())
                .productInfo(new ProductInfoEntity())
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "price"
                )
        );

    }

    @Test
    @DisplayName("Get all products test!")
    void ProductService_GetAllProducts_ReturnPageProductsEntities() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findAll(
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductResponse> productResponsePage = productServiceImplementation
                .getAllProducts(
                        pageable
                );

        Assertions.assertThat(productResponsePage).isNotNull();

    }

    @Test
    @DisplayName("Get single product details by url test!")
    void ProductService_GetSingleProductByUrl_ReturnProductEntity() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase(
                        "product"
                )
        ).thenReturn(Optional.of(product));

        ProductResponse productResponse = productServiceImplementation
                .getSingleProductDetails(
                        "product"
                );

        Assertions.assertThat(productResponse).isNotNull();

    }

    @Test
    @DisplayName("Get single product by url throw exception test!")
    void ProductService_GetSingleProductByUrl_ThrowException() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase(
                        "product"
                )
        ).thenReturn(Optional.of(product));

       assertThrows(
               ProductNotFoundException.class,
               () -> productServiceImplementation.getSingleProductDetails(
                       "product1"
               )
       );

    }

    @Test
    @DisplayName("Get products by category test!")
    void ProductService_GetProductsByCategory_ReturnPageProductsEntities() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findByCategoryUrl(
                        "category",
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductResponse> productResponsePage = productServiceImplementation
                .getProductsByCategory(
                        "category",
                        pageable
                );

        Assertions.assertThat(productResponsePage).isNotNull();

    }

    @Test
    @DisplayName("Get products by brand test!")
    void ProductService_GetProductsByBrand_ReturnPageProductsEntities() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findByBrandUrl(
                        "brand",
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductResponse> productResponsePage = productServiceImplementation
                .getProductsByBrand(
                        "brand",
                        pageable
                );

        Assertions.assertThat(productResponsePage).isNotNull();

    }

    @Test
    @DisplayName("Search products by name")
    void ProductService_SearchProducts_ReturnPageProductsEntities() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.searchByNameIgnoreCase(
                        "product",
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductResponse> productResponsePage = productServiceImplementation
                .searchProducts(
                        "product",
                        pageable
                );

        Assertions.assertThat(productResponsePage).isNotNull();

    }

}