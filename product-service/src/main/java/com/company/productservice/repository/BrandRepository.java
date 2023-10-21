package com.company.productservice.repository;

import com.company.productservice.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> searchByNameIgnoreCase(String name);
    Optional<BrandEntity> findByUrlIgnoreCase(String brandUrl);
    @Transactional
    Optional<BrandEntity> deleteByUrlIgnoreCase(String brandUrl);
    boolean existsByUrlIgnoreCase(String brandUrl);
}
