package com.company.productservice.service.implementation;

import com.company.productservice.dto.brand.BrandAdminResponse;
import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.product.ProductAdminResponse;
import com.company.productservice.dto.product.ProductDetailsAdminResponse;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.dto.product_info.ProductInfoRequest;
import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.exception.BrandNotFoundException;
import com.company.productservice.exception.CategoryNotFoundException;
import com.company.productservice.exception.InvalidVersionException;
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.mapper.BrandMapper;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.mapper.ProductMapper;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.repository.ProductInfoRepository;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.productservice.mapper.BrandMapper.mapRequestToBrandEntity;
import static com.company.productservice.mapper.BrandMapper.mapToBrandAdminResponse;
import static com.company.productservice.mapper.CategoryMapper.*;
import static com.company.productservice.mapper.ProductInfoMapper.mapRequestToProductInfoEntity;
import static com.company.productservice.mapper.ProductInfoMapper.mapToProductInfoResponse;
import static com.company.productservice.mapper.ProductMapper.*;
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
    public Page<CategoryAdminResponse> searchCategories(
            String name, Pageable pageable) {

       return categoryRepository
               .searchByNameIgnoreCase(name, pageable)
               .map(CategoryMapper::mapToCategoryAdminResponse);

    }

    @Override
    public CategoryAdminResponse getSingleCategory(String categoryUrl) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not get category by current url: " + categoryUrl + " !"
                        )
                );

        return mapToCategoryAdminResponse(category);

    }

    @Override
    public Page<CategoryAdminResponse> getSetOfCategoriesByBrand(
            String brandUrl, Pageable pageable) {

        return categoryRepository
                .findCategoryEntitiesByBrands_UrlIgnoreCase(brandUrl, pageable)
                .map(CategoryMapper::mapToCategoryAdminResponse);

    }

    @Override
    public CategoryAdminResponse createCategory(CategoryRequest categoryRequest) {

        CategoryEntity category = mapRequestToCategoryEntity(categoryRequest);

        category.setUrl(toSlug(categoryRequest.getName()));

        CategoryEntity createCategory = categoryRepository.save(category);

        return mapToCategoryAdminResponse(createCategory);

    }

    @Override
    public CategoryAdminResponse updateCategory(
            CategoryRequest categoryRequest, String categoryUrl) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not update category with current url: " + categoryUrl + " !"
                        )
                );

        if (!category.getVersion().equals(categoryRequest.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

        category.setName(categoryRequest.getName());
        category.setUrl(toSlug(categoryRequest.getName()));

        CategoryEntity updatedCategory = categoryRepository.save(category);

        return mapToCategoryAdminResponse(updatedCategory);

    }

    @Override
    @Transactional
    public void deleteCategory(String categoryUrl) {

        if (!categoryRepository.existsByUrlIgnoreCase(categoryUrl)) {
            throw new CategoryNotFoundException(
                    "Can not delete category by current url: " + categoryUrl + " !"
            );
        }

        categoryRepository.deleteByUrlIgnoreCase(categoryUrl);

    }

    @Override
    public Page<BrandAdminResponse> getAllBrands(Pageable pageable) {

        return brandRepository
                .findAll(pageable)
                .map(BrandMapper::mapToBrandAdminResponse);

    }

    @Override
    public Page<BrandAdminResponse> searchBrands(
            String name, Pageable pageable) {

        return brandRepository
                .searchByNameIgnoreCase(name, pageable)
                .map(BrandMapper::mapToBrandAdminResponse);

    }

    @Override
    public BrandAdminResponse getSingleBrand(String brandUrl) {

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not get brand by current url: " + brandUrl + " !"
                        )
                );

        return mapToBrandAdminResponse(brand);

    }

    @Override
    public Page<BrandAdminResponse> getSetOfBrandsByCategory(
            String categoryUrl, Pageable pageable) {

        return brandRepository
                .findBrandEntitiesByCategories_UrlIgnoreCase(categoryUrl, pageable)
                .map(BrandMapper::mapToBrandAdminResponse);

    }

    @Override
    public BrandAdminResponse createBrand(
            BrandRequest brandRequest, String categoryUrl) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not get category with current url: " + categoryUrl + " !"
                        )
                );

        Set<CategoryEntity> categoryEntitySet = new HashSet<>();
        categoryEntitySet.add(category);

        BrandEntity brand = mapRequestToBrandEntity(brandRequest);

        brand.setUrl(toSlug(brandRequest.getName()));
        brand.setCategories(categoryEntitySet);

        BrandEntity newBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(newBrand);

    }

    @Override
    public BrandAdminResponse updateBrand(
            BrandRequest brandRequest, String brandUrl) {

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not update brand by current url: " + brandUrl + " !"
                        )
                );

        if (!brand.getVersion().equals(brandRequest.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

        brand.setName(brandRequest.getName());
        brand.setUrl(toSlug(brandRequest.getName()));

        BrandEntity updatedBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(updatedBrand);

    }

    @Override
    @Transactional
    public void deleteBrand(String brandUrl) {

        if (!brandRepository.existsByUrlIgnoreCase(brandUrl)) {
            throw new BrandNotFoundException(
                    "Can not delete brand by current url: " + brandUrl + " !"
            );
        }

        brandRepository.deleteByUrlIgnoreCase(brandUrl);

    }

    @Override
    public Page<ProductAdminResponse> getAllProducts(Pageable pageable) {

        return productRepository
                .findAll(pageable)
                .map(ProductMapper::mapToProductAdminResponse);

    }

    @Override
    public ProductDetailsAdminResponse getSingleProductDetails(String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product by current url: " + productUrl + " !"
                        )
                );

        return mapToProductDetailsAdminResponse(product);

    }

    @Override
    public Page<ProductAdminResponse> searchProducts(
            String name, Pageable pageable) {

        return productRepository.searchByNameIgnoreCase(
                name,
                pageable
        ).map(ProductMapper::mapToProductAdminResponse);

    }

    @Override
    public Page<ProductAdminResponse> getProductsByCategory(
            String categoryUrl, Pageable pageable) {

       return productRepository
               .findByCategoryUrl(categoryUrl, pageable)
               .map(ProductMapper::mapToProductAdminResponse);

    }

    @Override
    public Page<ProductAdminResponse> getProductsByBrand(
            String brandUrl, Pageable pageable) {

        return productRepository
                .findByBrandUrl(brandUrl, pageable)
                .map(ProductMapper::mapToProductAdminResponse);

    }

    @Override
    public ProductAdminResponse createProduct(
            ProductRequest productRequest,
            String categoryUrl,
            String brandUrl) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        ()-> new CategoryNotFoundException(
                                "Can not get category with current url: " + categoryUrl + " !"
                        )
                );


        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not get brand by current url: " + brandUrl + " !"
                        )
                );

        ProductEntity product = mapRequestToProductEntity(productRequest);

        product.setUrl(toSlug(productRequest.getName()));
        product.setCategory(category);
        product.setBrand(brand);

        ProductEntity newProduct = productRepository.save(product);

        return mapToProductAdminResponse(newProduct);

    }

    @Override
    public ProductAdminResponse updateProduct(
            ProductRequest productRequest, String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not update product by current url: " + productUrl + " !"
                        )
                );

        if (!product.getVersion().equals(productRequest.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

        product.setName(productRequest.getName());
        product.setUrl(toSlug(productRequest.getName()));
        product.setPrice(productRequest.getPrice());
        product.setPhotoUrl(productRequest.getPhotoUrl());

        ProductEntity updatedProduct = productRepository.save(product);

        return mapToProductAdminResponse(updatedProduct);

    }

    @Override
    @Transactional
    public void deleteProduct(String productUrl) {

        if (!productRepository.existsByUrlIgnoreCase(productUrl)) {
            throw new ProductNotFoundException(
                    "Can not delete product by current url: " + productUrl + " !"
            );
        }

        productRepository.deleteByUrlIgnoreCase(productUrl);

    }

    @Override
    public ProductInfoResponse createProductDescription(
            ProductInfoRequest productInfoRequest, String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product by current url: " + productUrl + " !"
                        )
                );

        ProductInfoEntity productInfo = mapRequestToProductInfoEntity(productInfoRequest);

        productInfo.setProduct(product);

        ProductInfoEntity newProductInfo = productInfoRepository.save(productInfo);

        return mapToProductInfoResponse(newProductInfo);

    }

    @Override
    public ProductInfoResponse updateProductDescription(
            ProductInfoRequest productInfoRequest, String productUrl) {

        ProductInfoEntity productInfo = productInfoRepository
                .findByProductUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product info by current product url: " + productUrl + " !"
                        )
                );

        if (productInfo.getVersion().equals(productInfoRequest.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

       productInfo.setVersion(productInfoRequest.getVersion());
       productInfo.setTitle(productInfoRequest.getTitle());
       productInfo.setDescription(productInfoRequest.getDescription());
       productInfo.setSeries(productInfoRequest.getSeries());
       productInfo.setHeight(productInfoRequest.getHeight());
       productInfo.setWidth(productInfoRequest.getWidth());
       productInfo.setWeight(productInfoRequest.getWeight());
       productInfo.setOs(productInfoRequest.getOs());
       productInfo.setDisplay(productInfoRequest.getDisplay());
       productInfo.setResolution(productInfoRequest.getResolution());
       productInfo.setCpu(productInfoRequest.getCpu());
       productInfo.setGraphicCard(productInfoRequest.getGraphicCard());
       productInfo.setGpu(productInfoRequest.getGpu());
       productInfo.setRamType(productInfoRequest.getRamType());
       productInfo.setRam(productInfoRequest.getRam());
       productInfo.setMemoryType(productInfoRequest.getMemoryType());
       productInfo.setMemory(productInfoRequest.getMemory());

       ProductInfoEntity updatedProductInfo = productInfoRepository.save(productInfo);

       return mapToProductInfoResponse(updatedProductInfo);

    }

    @Override
    @Transactional
    public void deleteProductDescription(String productUrl) {

        if (!productInfoRepository.existsByProductUrl(productUrl)) {
            throw new ProductNotFoundException(
                    "Can not delete product info by current product url: " + productUrl + " !"
            );
        }

        productInfoRepository.deleteByProductUrl(productUrl);

    }

}
