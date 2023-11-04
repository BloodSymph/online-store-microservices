package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private BrandEntity brand;

    private CategoryEntity category;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        Set<CategoryEntity> categoryEntitySet = new HashSet<>();
        categoryEntitySet.add(category);

        Set<BrandEntity> brandEntitySet = new HashSet<>();
        brandEntitySet.add(brand);

        category = CategoryEntity.builder()
                .id(1L)
                .name("Category")
                .url("category")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .brands(brandEntitySet)
                .build();

        brand = BrandEntity.builder()
                .id(1L)
                .name("Brand")
                .url("brand")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .categories(categoryEntitySet)
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get brand by category url test!")
    void BrandRepository_GetBrandsByCategoryUrl_ReturnPageBrandEntity() {

        brandRepository.save(brand);

        categoryRepository.save(category);

        Page<BrandEntity> result = brandRepository
                .findBrandEntitiesByCategories_UrlIgnoreCase(
                        "category", pageable
                );

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Search brand by name test!")
    void BrandRepository_SearchBrandByName_ReturnPageBrandEntity() {

        brandRepository.save(brand);

        Page<BrandEntity> result = brandRepository
                .searchByNameIgnoreCase(
                        "brand", pageable
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Get brand by url test!")
    void BrandRepository_GetBrandByUrl_ReturnBrandEntity() {

        brandRepository.save(brand);

        Optional<BrandEntity> result = brandRepository
                .findByUrlIgnoreCase(
                        "brand"
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Delete brand by url test!")
    void BrandRepository_DeleteBrandByUrl_ReturnNone() {

        brandRepository.save(brand);

        org.junit.jupiter.api.Assertions.assertAll(
                () -> brandRepository
                        .deleteByUrlIgnoreCase(
                                "brand"
                        )
        );

    }

    @Test
    @DisplayName("Brand dose exist by url test!")
    void BrandRepository_BrandExistByUrl_ReturnTrue() {

        brandRepository.save(brand);

        Boolean result = brandRepository
                .existsByUrlIgnoreCase(
                        "brand"
                );

        Assertions.assertThat(result).isTrue();

    }

}