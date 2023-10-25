package com.company.productservice.repository;

import com.company.productservice.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.category.url LIKE LOWER(:categoryUrl)"
    )
    List<ProductEntity> findByCategoryUrl(
           @Param("categoryUrl") String categoryUrl
    );

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.brand.url LIKE LOWER(:brandUrl)"
    )
    List<ProductEntity> findByBrandUrl(
           @Param("brandUrl") String brandUrl
    );

    Optional<ProductEntity> findByUrlIgnoreCase(String productUrl);

    @Query(
        "SELECT products FROM ProductEntity products " +
        "WHERE products.name LIKE LOWER(:productName) " +
        "OR products.category.name LIKE LOWER(:categoryName) " +
        "OR products.brand.name LIKE LOWER(:brandName)"
    )
    List<ProductEntity> searchProduct(
            @Param("productName") String productName,
            @Param("categoryName") String categoryName,
            @Param("brandName") String brandName
    );

    @Transactional
    Optional<ProductEntity> deleteByUrlIgnoreCase(String productUrl);

    Boolean existsByUrlIgnoreCase(String productUrl);

}
