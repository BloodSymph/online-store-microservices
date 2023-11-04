package com.company.productservice.repository;

import com.company.productservice.entity.ProductInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, Long> {

    @Query(
        "SELECT product_info FROM ProductInfoEntity product_info " +
        "WHERE product_info.product.url LIKE LOWER(:productUrl)"
    )
    Optional<ProductInfoEntity> findByProductUrlIgnoreCase(
           @Param("productUrl") String productUrl
    );

    @Transactional
    @Modifying
    @Query(
        "DELETE FROM ProductInfoEntity product_info " +
        "WHERE product_info.product.url LIKE LOWER(:productUrl)"
    )
    void deleteByProductUrl(
            @Param("productUrl") String productUrl
    );

    Boolean existsByProductUrl(String productUrl);

}
