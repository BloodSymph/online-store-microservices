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
import java.util.*;


@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    private CategoryEntity category;

    private BrandEntity brand;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        Set<CategoryEntity> categoryEntitySet = new HashSet<>();
        categoryEntitySet.add(category);

        Set<BrandEntity> brandEntitySet = new HashSet<>();
        brandEntitySet.add(brand);

         category = CategoryEntity.builder()
                .version(1L)
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
    @DisplayName("Get category by brand url test!")
    void CategoryRepository_GetCategoriesByBrandUrl_ReturnPageCategoryEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        Page<CategoryEntity> result = categoryRepository
                .findCategoryEntitiesByBrands_UrlIgnoreCase(
                        "brand", pageable
                );

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Get category by url test!")
    void CategoryRepository_GetCategoryByUrl_ReturnCategoryEntity() {

        categoryRepository.save(category);

        Optional<CategoryEntity> result = categoryRepository
                .findByUrlIgnoreCase(
                        "category"
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Search category by name test!")
    void CategoryRepository_SearchByName_ReturnPageCategoryEntity() {

        categoryRepository.save(category);

        Page<CategoryEntity> result = categoryRepository
                .searchByNameIgnoreCase(
                        "category",
                        pageable
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Delete category by url test!")
    void CategoryRepository_DeleteByUrl_ReturnNone() {

        categoryRepository.save(category);

        org.junit.jupiter.api.Assertions.assertAll(
                () -> categoryRepository
                        .deleteByUrlIgnoreCase(
                                "category"
                        )
        );

    }

    @Test
    @DisplayName("Category dose exist by url test!")
    void CategoryRepository_ExistByUrl_ReturnTrue() {

        categoryRepository.save(category);

        Boolean result = categoryRepository
                .existsByUrlIgnoreCase(
                        "category"
                );

        Assertions.assertThat(result).isTrue();

    }
}