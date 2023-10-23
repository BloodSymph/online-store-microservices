package com.company.productservice.service.implementation;

import com.company.productservice.dto.brand.BrandCategoriesResponse;
import com.company.productservice.dto.brand.BrandProductsResponse;
import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.mapper.BrandMapper;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.BrandMapper.mapToBrandCategoriesResponse;
import static com.company.productservice.mapper.BrandMapper.mapToBrandProductsResponse;

@Service
@RequiredArgsConstructor
public class BrandServiceImplementation implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandResponse> getAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();
        return brands.stream()
                .map(BrandMapper::mapToBrandResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandCategoriesResponse getCategoriesBySelectedBrand(String brandUrl) {
        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException("Can not get categories by selected brand!")
                );
        return mapToBrandCategoriesResponse(brand);
    }

    @Override
    public BrandProductsResponse getProductsBySelectedBrand(String brandUrl) {
        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException("Can not get products by selected brand!")
                );
        return mapToBrandProductsResponse(brand);
    }
}
