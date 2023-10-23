package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    // TODO: 23.10.2023 Create custom query JDBC for search brands set by category name or url
    List<BrandEntity> searchByNameIgnoreCase(String name);
    Optional<BrandEntity> findByUrlIgnoreCase(String brandUrl);
    @Transactional
    Optional<BrandEntity> deleteByUrlIgnoreCase(String brandUrl);
    boolean existsByUrlIgnoreCase(String brandUrl);
}
