package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BrandService {

    Page<BrandResponse> getAllBrands(Pageable pageable);

    BrandResponse getCurrentBrand(String brandUrl);

    Page<BrandResponse> searchBrands(
            String name, Pageable pageable
    );

    Page<BrandResponse> getBrandsByCategory(
            String categoryUrl, Pageable pageable
    );

}
