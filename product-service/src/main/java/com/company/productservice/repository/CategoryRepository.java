package com.company.productservice.repository;

import com.company.productservice.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);
    List<CategoryEntity> searchByNameIgnoreCase(String name);
    @Transactional
    Optional<CategoryEntity> deleteByUrlIgnoreCase(String categoryUrl);
    boolean existsByUrlIgnoreCase(String categoryUrl);
}
