package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    private BrandEntity brand;

    @BeforeEach
    void setUp() {

        brand = BrandEntity.builder()
                .id(1L)
                .name("Test")
                .url("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .categories(new HashSet<>())
                .products(new HashSet<>())
                .build();

    }

    @Test
    @DisplayName("Search brand by name ignore case test!")
    void BrandRepository_SearchByNameIgnoreCase_ReturnListBrandEntities() {

        List<BrandEntity> brandEntities = new ArrayList<>();

        brandEntities.add(brand);

        brandRepository.saveAll(brandEntities);

        List<BrandEntity> result = brandRepository.searchByNameIgnoreCase("test");

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Find brand by url test!")
    void BrandRepository_FindByUrlIgnoreCase_ReturnBrandEntity() {

        brandRepository.save(brand);

        Optional<BrandEntity> result = brandRepository.findByUrlIgnoreCase("test");

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Delete brand by url test!")
    void BrandRepository_DeleteByUrl_ReturnNone() {

        brandRepository.save(brand);

        Optional<BrandEntity> result = brandRepository.deleteByUrlIgnoreCase("test");

        Assertions.assertThat(result).isNotIn(brand);

    }

    @Test
    void BrandRepository_ExistByUrl_ReturnTrue() {

        brandRepository.save(brand);

        boolean result = brandRepository.existsByUrlIgnoreCase("test");

        Assertions.assertThat(result).isTrue();

    }
}