package com.company.productservice.repository;

import com.company.productservice.entity.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryEntity category;

    @BeforeEach
    void setUp() {

        category = CategoryEntity.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .brands(new HashSet<>())
                .products(new HashSet<>())
                .build();

    }

    @Test
    @DisplayName("Finding category by url test")
    void CategoryRepository_FindByUrlIgnoreCase_ReturnCategoryEntity() {

        categoryRepository.save(category);

        CategoryEntity result = categoryRepository.findByUrlIgnoreCase("test").get();

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Search category by name test")
    void CategoryRepository_SearchByUrlIgnoreCase_ReturnListCategoryEntities() {

        List<CategoryEntity> categoryList = new ArrayList<>();

        categoryList.add(category);

        categoryRepository.saveAll(categoryList);

        List<CategoryEntity> result = categoryRepository.searchByNameIgnoreCase("test");

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Category delete by url test!")
    void CategoryRepository_DeleteByUrlIgnoreCase_ReturnNone() {

        categoryRepository.save(category);

        Optional<CategoryEntity> result = categoryRepository.deleteByUrlIgnoreCase("test");

        Assertions.assertThat(result).isNotIn(category);

    }

    @Test
    @DisplayName("Category dose exists by url test")
    void CategoryRepository_ExistsByUrlIgnoreCase_ReturnTrue() {

        categoryRepository.save(category);

        boolean result = categoryRepository.existsByUrlIgnoreCase("test");

        Assertions.assertThat(result).isTrue();

    }
}