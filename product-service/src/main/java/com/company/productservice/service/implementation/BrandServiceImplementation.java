package com.company.productservice.service.implementation;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.mapper.BrandMapper;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.company.productservice.mapper.BrandMapper.mapToBrandResponse;

@Service
@RequiredArgsConstructor
public class BrandServiceImplementation implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Page<BrandResponse> getAllBrands(Pageable pageable) {
        return brandRepository
                .findAll(pageable)
                .map(BrandMapper::mapToBrandResponse);
    }

    @Override
    public BrandResponse getCurrentBrand(String brandUrl) {
        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not get brand by current url: " + brandUrl
                        )
                );
        return mapToBrandResponse(brand);
    }

    @Override
    public Page<BrandResponse> searchBrands(
            String name, Pageable pageable
    ) {
        return brandRepository
                .searchByNameIgnoreCase(name, pageable)
                .map(BrandMapper::mapToBrandResponse);
    }

    @Override
    public Page<BrandResponse> getBrandsByCategory(
            String categoryUrl, Pageable pageable
    ) {
        return brandRepository
                .findBrandEntitiesByCategories_UrlIgnoreCase(
                        categoryUrl, pageable
                ).map(BrandMapper::mapToBrandResponse);
    }

}
