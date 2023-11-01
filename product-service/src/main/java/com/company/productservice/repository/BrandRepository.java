package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query(
        "SELECT brands FROM BrandEntity brands " +
        "INNER JOIN brands.categories categories ON categories.url " +
        "LIKE LOWER(:categoryUrl)"
    )
    Page<BrandEntity> findBrandEntitiesByCategories_UrlIgnoreCase(
            @Param("categoryUrl") String categoryUrl,
            Pageable pageable
    );

    Page<BrandEntity> searchByNameIgnoreCase(String name, Pageable pageable);

    Optional<BrandEntity> findByUrlIgnoreCase(String brandUrl);

    @Transactional
    void deleteByUrlIgnoreCase(String brandUrl);

    Boolean existsByUrlIgnoreCase(String brandUrl);

}
