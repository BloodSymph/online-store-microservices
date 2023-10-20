package com.company.productservice.repository;

import com.company.productservice.entity.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Finding category by url test")
    void findByUrlIgnoreCase() {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Test");
        category.setUrl("test-url");

        categoryRepository.save(category);

        String url = "test-url";

        CategoryEntity result = categoryRepository.findByUrlIgnoreCase(url).get();

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Search category by name test")
    void searchByNameIgnoreCase() {
        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1L);
        category1.setName("Test");
        category1.setUrl("test-url");

        CategoryEntity category2 = new CategoryEntity();
        category2.setId(2L);
        category2.setName("Test 2");
        category2.setUrl("test-url-2");

        Set<CategoryEntity> categoryList = new HashSet<>();
        categoryList.add(category1);
        categoryList.add(category2);

        categoryRepository.saveAll(categoryList);

        String name = "test 2";

        Set<CategoryEntity> result = categoryRepository.searchByNameIgnoreCase(name);

        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    void deleteByUrlIgnoreCase() {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Test");
        category.setUrl("test-url");

        categoryRepository.save(category);

        String url = "test-url";

        Optional<CategoryEntity> result = categoryRepository.deleteByUrlIgnoreCase(url);

        Assertions.assertThat(result).isNotIn(category);
    }

    @Test
    @DisplayName("Category dose exists by url test")
    void existsByUrlIgnoreCase() {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Test");
        category.setUrl("test-url");

        categoryRepository.save(category);

        String url = "test-url";

        boolean result = categoryRepository.existsByUrlIgnoreCase(url);

        Assertions.assertThat(result).isTrue();
    }
}