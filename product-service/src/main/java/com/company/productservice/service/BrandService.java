package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandCategoriesResponse;
import com.company.productservice.dto.brand.BrandProductsResponse;
import com.company.productservice.dto.brand.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandResponse> getAllBrands();
    BrandCategoriesResponse getCategoriesBySelectedBrand(String brandUrl);
    BrandProductsResponse getProductsBySelectedBrand(String brandUrl);
}
