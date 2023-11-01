package com.company.productservice.repository;

import com.company.productservice.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.category.url LIKE LOWER(:categoryUrl)"
    )
    Page<ProductEntity> findByCategoryUrl(
           @Param("categoryUrl") String categoryUrl,
           Pageable pageable
    );

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.brand.url LIKE LOWER(:brandUrl)"
    )
    Page<ProductEntity> findByBrandUrl(
           @Param("brandUrl") String brandUrl,
           Pageable pageable
    );

    Optional<ProductEntity> findByUrlIgnoreCase(String productUrl);

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.name LIKE LOWER(:productName) " +
        "OR products.category.name LIKE LOWER(:categoryName) " +
        "OR products.brand.name LIKE LOWER(:brandName)"
    )
    Page<ProductEntity> searchProduct(
            @Param("productName") String productName,
            @Param("categoryName") String categoryName,
            @Param("brandName") String brandName,
            Pageable pageable
    );

    @Transactional
    void deleteByUrlIgnoreCase(String productUrl);

    Boolean existsByUrlIgnoreCase(String productUrl);

}
