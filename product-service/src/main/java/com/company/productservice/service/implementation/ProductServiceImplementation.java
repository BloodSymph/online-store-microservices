package com.company.productservice.service.implementation;

import com.company.productservice.dto.product.ProductDetailsResponse;
import com.company.productservice.dto.product.ProductResponse;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.mapper.ProductMapper;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.company.productservice.mapper.ProductMapper.mapToProductDetailsResponse;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {

        return productRepository
                .findAll(pageable)
                .map(ProductMapper::mapToProductResponse);

    }

    @Override
    public ProductDetailsResponse getSingleProductDetails(String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product by current url: " + productUrl + " !"
                        )
                );

        return mapToProductDetailsResponse(product);

    }

    @Override
    public Page<ProductResponse> getProductsByCategory(
            String categoryUrl, Pageable pageable) {

        return productRepository
                .findByCategoryUrl(
                        categoryUrl, pageable
                ).map(ProductMapper::mapToProductResponse);

    }

    @Override
    public Page<ProductResponse> getProductsByBrand(
            String brandUrl, Pageable pageable) {

        return productRepository
                .findByBrandUrl(
                        brandUrl, pageable
                ).map(ProductMapper::mapToProductResponse);

    }

    @Override
    public Page<ProductResponse> searchProducts(
            String name, Pageable pageable) {

        return productRepository
                .searchByNameIgnoreCase(
                        name,
                        pageable
                ).map(ProductMapper::mapToProductResponse);

    }

    @Override
    public ProductResponse getProductByIdForCart(Long productId) {
        return productRepository
                .findById(productId)
                .map(ProductMapper::mapToProductResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not get product by id: " + productId + " !"
                        )
                );
    }

}
