package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query(
        "SELECT brands FROM BrandEntity brands " +
        "JOIN CategoryEntity categories WHERE categories.url " +
        "LIKE LOWER(:categoryUrl)"
    )
    Set<BrandEntity> findBrandEntitiesByCategories_UrlIgnoreCase(
            @Param("categoryUrl") String categoryUrl
    );

    List<BrandEntity> searchByNameIgnoreCase(String name);

    Optional<BrandEntity> findByUrlIgnoreCase(String brandUrl);

    @Transactional
    Optional<BrandEntity> deleteByUrlIgnoreCase(String brandUrl);

    Boolean existsByUrlIgnoreCase(String brandUrl);

}
