package com.company.productservice.repository;

import com.company.productservice.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(
        "SELECT categories FROM CategoryEntity categories " +
        "JOIN BrandEntity brands WHERE brands.url " +
        "LIKE LOWER(:brandUrl)"
    )
    Set<CategoryEntity> findCategoryEntitiesByBrands_UrlIgnoreCase(
            @Param("categoryUrl") String brandUrl
    );
    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);
    List<CategoryEntity> searchByNameIgnoreCase(String name);
    @Transactional
    Optional<CategoryEntity> deleteByUrlIgnoreCase(String categoryUrl);
    boolean existsByUrlIgnoreCase(String categoryUrl);
}
