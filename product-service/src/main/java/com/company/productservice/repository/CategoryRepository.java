package com.company.productservice.repository;

import com.company.productservice.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(
            "SELECT categories " +
            "FROM CategoryEntity categories " +
            "INNER JOIN categories.brands brands " +
            "ON brands.url LIKE LOWER(:brandUrl) "
    )
    Page<CategoryEntity> findCategoryEntitiesByBrands_UrlIgnoreCase(
            @Param("brandUrl") String brandUrl,
            Pageable pageable
    );

    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);

    Page<CategoryEntity> searchByNameIgnoreCase(
            String name, Pageable pageable
    );

    void deleteByUrlIgnoreCase(String categoryUrl);

    Boolean existsByUrlIgnoreCase(String categoryUrl);

}
