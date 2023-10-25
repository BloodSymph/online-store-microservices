package com.company.productservice.service.implementation;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.dto.product_info.ProductInfoRequest;
import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.mapper.BrandMapper;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.mapper.ProductMapper;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.repository.ProductInfoRepository;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.BrandMapper.mapRequestToBrandEntity;
import static com.company.productservice.mapper.BrandMapper.mapToBrandAdminResponse;
import static com.company.productservice.mapper.CategoryMapper.*;
import static com.company.productservice.mapper.ProductMapper.mapToProductAdminResponse;
import static com.company.productservice.utils.SlugGenerator.toSlug;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    private final ProductInfoRepository productInfoRepository;

    @Override
    public Page<CategoryAdminResponse> getAllCategories(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(CategoryMapper::mapToCategoryAdminResponse);
    }

    @Override
    public List<CategoryAdminResponse> searchCategories(String name) {
        List<CategoryEntity> categoryEntityList = categoryRepository.searchByNameIgnoreCase(name);
        return categoryEntityList.stream()
                .map(CategoryMapper::mapToCategoryAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAdminResponse getSingleCategory(String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Can not get category by current url: " + categoryUrl)
                );
        return mapToCategoryAdminResponse(category);
    }

    @Override
    public Set<CategoryAdminResponse> getSetOfCategoriesByBrand(String brandUrl) {
        Set<CategoryEntity> categoryEntitySet = categoryRepository.findCategoryEntitiesByBrands_UrlIgnoreCase(brandUrl);
        return categoryEntitySet.stream()
                .map(CategoryMapper::mapToCategoryAdminResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public CategoryAdminResponse createCategory(CategoryRequest categoryRequest) {

        CategoryEntity category = mapRequestToCategoryEntity(categoryRequest);

        category.setName(categoryRequest.getName());
        category.setUrl(toSlug(categoryRequest.getName()));

        CategoryEntity createCategory = categoryRepository.save(category);

        return mapToCategoryAdminResponse(createCategory);

    }

    @Override
    public CategoryAdminResponse updateCategory(
            CategoryRequest categoryRequest, String categoryUrl
    ) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not update category with current url: " + categoryUrl
                        )
                );

        category.setName(categoryRequest.getName());
        category.setUrl(toSlug(categoryRequest.getName()));

        CategoryEntity updatedCategory = categoryRepository.save(category);

        return mapToCategoryAdminResponse(updatedCategory);

    }

    @Override
    public void deleteCategory(String categoryUrl) {
        if (!categoryRepository.existsByUrlIgnoreCase(categoryUrl)) {
            throw new CategoryNotFoundException(
                    "Can not delete category by current url: " + categoryUrl
            );
        }
        categoryRepository.deleteByUrlIgnoreCase(categoryUrl);
    }

    @Override
    public List<BrandAdminResponse> getAllBrands(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);
        List<BrandEntity> brands = brandEntityPage.getContent();
        return brands.stream()
                .map(BrandMapper::mapToBrandAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandAdminResponse> searchBrands(String name) {
        List<BrandEntity> brands = brandRepository.searchByNameIgnoreCase(name);
        return brands.stream()
                .map(BrandMapper::mapToBrandAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandAdminResponse getSingleBrand(String brandUrl) {
        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException("Can not get brand by current url: " + brandUrl)
                );
        return mapToBrandAdminResponse(brand);
    }

    @Override
    public Set<BrandAdminResponse> getSetOfBrandsByCategory(String categoryUrl) {
        Set<BrandEntity> brands = brandRepository.findBrandEntitiesByCategories_UrlIgnoreCase(categoryUrl);
        return brands.stream()
                .map(BrandMapper::mapToBrandAdminResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public BrandAdminResponse createBrand(
            BrandRequest brandRequest, String categoryUrl
    ) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not get category with current url: " + categoryUrl
                        )
                );

        Set<CategoryEntity> categoryEntitySet = new HashSet<>();
        categoryEntitySet.add(category);

        BrandEntity brand = mapRequestToBrandEntity(brandRequest);
        brand.setName(brandRequest.getName());
        brand.setUrl(toSlug(brandRequest.getName()));
        brand.setCategories(categoryEntitySet);

        BrandEntity newBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(newBrand);

    }

    @Override
    public BrandAdminResponse updateBrand(
            BrandRequest brandRequest, String brandUrl
    ) {

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException("Can not update brand by current url: " + brandUrl)
                );

        brand.setName(brandRequest.getName());
        brand.setUrl(toSlug(brandRequest.getName()));

        BrandEntity updatedBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(updatedBrand);

    }

    @Override
    public void deleteBrand(String brandUrl) {
        if (!brandRepository.existsByUrlIgnoreCase(brandUrl)) {
            throw new BrandNotFoundException(
                    "Can not delete brand by current url: " + brandUrl
            );
        }
        brandRepository.deleteByUrlIgnoreCase(brandUrl);
    }

    @Override
    public List<ProductAdminResponse> getAllProducts(
            int pageNumber, int pageSize, String sort
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by(sort).descending()
        );
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        List<ProductEntity> products = productEntityPage.getContent();
        return products.stream()
                .map(ProductMapper::mapToProductAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductAdminResponse getSingleProduct(String productUrl) {
        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException("Can not get product by current url: " + productUrl)
                );
        return mapToProductAdminResponse(product);
    }

    @Override
    public List<ProductAdminResponse> searchProducts(
            String productName, String categoryName, String brandName
    ) {

        List<ProductEntity> products = productRepository.searchProduct(
                productName, categoryName, brandName
        );

        return products.stream()
                .map(ProductMapper::mapToProductAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductAdminResponse> getProductsByCategory(String categoryUrl) {
        List<ProductEntity> products = productRepository.findByCategoryUrl(categoryUrl);
        return products.stream()
                .map(ProductMapper::mapToProductAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductAdminResponse> getProductsByBrand(String brandUrl) {
        List<ProductEntity> products = productRepository.findByBrandUrl(brandUrl);
        return products.stream()
                .map(ProductMapper::mapToProductAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductAdminResponse createProduct(ProductRequest productRequest, String categoryUrl, String brandUrl) {

        return null;
    }

    @Override
    public ProductAdminResponse updateProduct(ProductRequest productRequest, String productUrl) {
        return null;
    }

    @Override
    public void deleteProduct(String productUrl) {
        if (!productRepository.existsByUrlIgnoreCase(productUrl)) {
            throw new ProductNotFoundException(
                    "Can not delete product by current url: " + productUrl
            );
        }
        productRepository.deleteByUrlIgnoreCase(productUrl);
    }

    @Override
    public ProductInfoResponse getProductInfo(String productUrl) {
        return null;
    }

    @Override
    public ProductInfoResponse createProductDescription(ProductInfoRequest productInfoRequest, String productUrl) {
        return null;
    }

    @Override
    public ProductInfoResponse updateProductDescription(ProductInfoRequest productInfoRequest, String productUrl) {
        return null;
    }

    @Override
    public void deleteProductDescription(String productUrl) {

    }

}
