package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;




@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    private CategoryEntity category;

    private BrandEntity brand;

    private ProductEntity product;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        Set<CategoryEntity> categoryEntitySet = new HashSet<>();
        categoryEntitySet.add(category);

        Set<BrandEntity> brandEntitySet = new HashSet<>();
        brandEntitySet.add(brand);

        Set<ProductEntity> productEntitySet = new HashSet<>();
        productEntitySet.add(product);

        category = CategoryEntity.builder()
                .id(1L)
                .name("Category")
                .url("category")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(productEntitySet)
                .brands(brandEntitySet)
                .build();

        brand = BrandEntity.builder()
                .id(1L)
                .name("Brand")
                .url("brand")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(productEntitySet)
                .categories(categoryEntitySet)
                .build();

        product = ProductEntity.builder()
                .id(1L)
                .name("Product")
                .url("product")
                .price("100")
                .photoUrl("https://photo")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .category(category)
                .brand(brand)
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get product by category url test!")
    void ProductRepository_GetByCategoryUrl_ReturnPageProductEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        Page<ProductEntity> result = productRepository
                .findByCategoryUrl(
                        "category", pageable
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Get product by brand url test!")
    void ProductRepository_GetByBrandUrl_ReturnPageProductEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        Page<ProductEntity> result = productRepository
                .findByBrandUrl(
                        "brand", pageable
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Get product by url test!")
    void ProductRepository_GetProductByUrl_ReturnProductEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        Optional<ProductEntity> result = productRepository
                .findByUrlIgnoreCase(
                        "product"
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Search product by queries test!")
    void ProductRepository_SearchProductByQueries_ReturnPageProductEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        Page<ProductEntity> result = productRepository
                .searchByNameIgnoreCase(
                        "Product",
                        pageable
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Delete product by url test!")
    void ProductRepository_DeleteProductByUrl_ReturnNone() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        org.junit.jupiter.api.Assertions.assertAll(
                () -> productRepository
                        .deleteByUrlIgnoreCase(
                                "product"
                        )
        );

    }

    @Test
    @DisplayName("Product dose exist by url test!")
    void ProductRepository_ExistByUrl_ReturnTrue() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        Boolean result = productRepository.existsByUrlIgnoreCase("product");

        Assertions.assertThat(result).isTrue();

    }

}