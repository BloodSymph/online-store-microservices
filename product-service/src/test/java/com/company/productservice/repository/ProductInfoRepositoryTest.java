package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
class ProductInfoRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    private CategoryEntity category;

    private BrandEntity brand;

    private ProductEntity product;

    private ProductInfoEntity productInfo;


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
                .productInfo(productInfo)
                .build();

        productInfo = ProductInfoEntity.builder()
                .id(1L)
                .title("Title")
                .description("Description")
                .series("series")
                .height("15")
                .width("5")
                .weight("250")
                .os("Os")
                .display("Display")
                .resolution("Resolution")
                .cpu("Cpu")
                .graphicCard("Graphic card")
                .gpu("Gpu")
                .ramType("Ram Type")
                .ram("Ram")
                .memoryType("Memory Type")
                .memory("Memory")
                .product(product)
                .build();

    }

    @Test
    @DisplayName("Get info by product test!")
    void ProductInfoRepository_GetInfoByProductUrl_ReturnProductInfoEntity() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        productInfoRepository.save(productInfo);

        Optional<ProductInfoEntity> result = productInfoRepository
                .findByProductUrlIgnoreCase(
                        "product"
                );

        Assertions.assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Delete info by product test!")
    void ProductInfoRepository_DeleteInfoByUrl_ReturnNone() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        productInfoRepository.save(productInfo);

        org.junit.jupiter.api.Assertions.assertAll(
                () -> productInfoRepository
                        .deleteByProductUrl(
                                "product"
                        )
        );

    }

    @Test
    @DisplayName("Info dose exist by product test!")
    void ProductInfoRepository_ExistByUrl_ReturnTrue() {

        categoryRepository.save(category);

        brandRepository.save(brand);

        productRepository.save(product);

        productInfoRepository.save(productInfo);

        Boolean result = productInfoRepository
                .existsByProductUrl(
                        "product"
                );

        Assertions.assertThat(result).isTrue();

    }

}