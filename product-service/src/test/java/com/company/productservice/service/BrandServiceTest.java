package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.service.implementation.BrandServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImplementation brandServiceImplementation;

    private BrandEntity brand;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        brand = BrandEntity.builder()
                .id(1L)
                .name("Brand")
                .url("brand")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .categories(new HashSet<>())
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }

    @Test
    @DisplayName("Get all brands test!")
    void BrandService_GetAllBrands_ReturnPageBrandsEntities() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.findAll(pageable)
        ).thenReturn(brandEntityPage);

        Page<BrandResponse> brandResponsePage = brandServiceImplementation
                .getAllBrands(
                        pageable
                );

        Assertions.assertThat(brandResponsePage).isNotNull();

    }

    @Test
    @DisplayName("Get brand by url test")
    void BrandService_GetSingleBrand_ReturnBrandEntity() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase("brand")
        ).thenReturn(Optional.of(brand));

       BrandResponse brandResponse = brandServiceImplementation
                .getCurrentBrand(
                        "brand"
                );

       Assertions.assertThat(brandResponse).isNotNull();

    }

    @Test
    @DisplayName("Get brand by url throw exception test test")
    void BrandService_GetSingleBrand_ThrowException() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase("brand")
        ).thenReturn(Optional.of(brand));

        assertThrows(
                BrandNotFoundException.class,
                () -> brandServiceImplementation.getCurrentBrand(
                        "brand1"
                )
        );

    }

    @Test
    @DisplayName("Search brand by name test!")
    void BrandService_SearchBrands_ReturnPageBrandsEntities() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.searchByNameIgnoreCase(
                        "brand",
                        pageable
                )
        ).thenReturn(brandEntityPage);

        Page<BrandResponse> brandResponsePage = brandServiceImplementation
                .searchBrands(
                        "brand",
                        pageable
                );

        Assertions.assertThat(brandResponsePage).isNotNull();

    }

    @Test
    @DisplayName("Get brands by category test!")
    void BrandService_GetBrandsByCategory_ReturnPageBrandsEntities() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.findBrandEntitiesByCategories_UrlIgnoreCase(
                        "category",
                        pageable
                )
        ).thenReturn(brandEntityPage);

        Page<BrandResponse> brandResponsePage = brandServiceImplementation
                .getBrandsByCategory(
                        "category",
                        pageable
                );

        Assertions.assertThat(brandResponsePage).isNotNull();

    }

}