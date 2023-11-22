package com.company.productservice.service;

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
import com.company.productservice.exception.ProductNotFoundException;
import com.company.productservice.repository.BrandRepository;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.repository.ProductInfoRepository;
import com.company.productservice.repository.ProductRepository;
import com.company.productservice.service.implementation.AdminServiceImplementation;
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

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductInfoRepository productInfoRepository;

    @InjectMocks
    private AdminServiceImplementation adminServiceImplementation;

    private CategoryEntity category;

    private BrandEntity brand;

    private ProductEntity product;

    private ProductInfoEntity productInfo;

    private CategoryRequest categoryRequest;

    private BrandRequest brandRequest;

    private ProductRequest productRequest;

    private ProductInfoRequest productInfoRequest;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        category = CategoryEntity.builder()
                .version(1L)
                .id(1L)
                .name("Category")
                .url("category")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .brands(new HashSet<>())
                .build();

        brand = BrandEntity.builder()
                .version(1L)
                .id(1L)
                .name("Brand")
                .url("brand")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(new HashSet<>())
                .categories(new HashSet<>())
                .build();

        product = ProductEntity.builder()
                .version(1L)
                .id(1L)
                .name("Product")
                .url("product")
                .price("100")
                .photoUrl("https://photo")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .category(new CategoryEntity())
                .brand(new BrandEntity())
                .productInfo(new ProductInfoEntity())
                .build();

        productInfo = ProductInfoEntity.builder()
                .version(1L)
                .id(1L)
                .title("Title")
                .description("Description")
                .series("series")
                .height("15")
                .width("5")
                .weight("250")
                .os("Os")
                .display("Display")
                .resolution("Resolution")
                .cpu("Cpu")
                .graphicCard("Graphic card")
                .gpu("Gpu")
                .ramType("Ram Type")
                .ram("Ram")
                .memoryType("Memory Type")
                .memory("Memory")
                .product(new ProductEntity())
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

        categoryRequest = CategoryRequest.builder()
                .version(1L)
                .name("Category")
                .url("category")
                .build();

        brandRequest = BrandRequest.builder()
                .version(1L)
                .name("Brand")
                .url("brand")
                .build();

        productRequest = ProductRequest.builder()
                .version(1L)
                .name("Product")
                .url("product")
                .price("1000$")
                .photoUrl("https://productPhoto.com")
                .build();

        productInfoRequest = ProductInfoRequest.builder()
                .version(1L)
                .title("Title")
                .description("Description")
                .series("series")
                .height("15")
                .width("5")
                .weight("250")
                .os("Os")
                .display("Display")
                .resolution("Resolution")
                .cpu("Cpu")
                .graphicCard("Graphic card")
                .gpu("Gpu")
                .ramType("Ram Type")
                .ram("Ram")
                .memoryType("Memory Type")
                .memory("Memory")
                .build();

    }

    @Test
    @DisplayName("Get all categories test!")
    void AdminService_GetAllCategories_ReturnPageCategoriesAdminResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.findAll(pageable)
        ).thenReturn(categoryEntityPage);

        Page<CategoryAdminResponse> categoryAdminResponses = adminServiceImplementation
                .getAllCategories(
                        pageable
                );

        Assertions.assertThat(categoryAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Search categories test!")
    void AdminService_SearchCategories_ReturnPageCategoriesAdminResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.searchByNameIgnoreCase(
                        "category",
                        pageable
                )
        ).thenReturn(categoryEntityPage);

        Page<CategoryAdminResponse> categoryAdminResponses = adminServiceImplementation
                .searchCategories(
                        "category",
                        pageable
                );

        Assertions.assertThat(categoryAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get category by url test!")
    void AdminService_GetSingleCategory_ReturnCategoryAdminResponse(){

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase("category")
        ).thenReturn(Optional.of(category));

        CategoryAdminResponse categoryAdminResponse = adminServiceImplementation
                .getSingleCategory(
                        "category"
                );

        Assertions.assertThat(categoryAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Get category by url throw exception test!")
    void AdminService_GetSingleCategory_ThrowException(){

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase("category")
        ).thenReturn(Optional.of(category));

        assertThrows(
                CategoryNotFoundException.class,
                () -> adminServiceImplementation.getSingleCategory(
                        "category1"
                )
        );

    }

    @Test
    @DisplayName("Get categories by brand test!")
    void AdminService_GetCategoriesByBrand_ReturnPageCategoriesAdminResponse() {

        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(category);

        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(categoryEntityList);

        Mockito.lenient().when(
                categoryRepository.findCategoryEntitiesByBrands_UrlIgnoreCase(
                        "brand",
                        pageable
                )
        ).thenReturn(categoryEntityPage);

        Page<CategoryAdminResponse> categoryAdminResponses = adminServiceImplementation
                .getSetOfCategoriesByBrand(
                        "brand",
                        pageable
                );

        Assertions.assertThat(categoryAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Create new category test!")
    void AdminService_CreateNewCategory_ReturnCategoryAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.save(Mockito.any(CategoryEntity.class))
        ).thenReturn(category);

        CategoryAdminResponse categoryAdminResponse = adminServiceImplementation
                .createCategory(
                        categoryRequest
                );

        Assertions.assertThat(categoryAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Update category test!")
    void AdminService_UpdateCategory_ReturnCategoryAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "category"
                )
        ).thenReturn(Optional.of(category));

        Mockito.lenient().when(
                categoryRepository.save(category)
        ).thenReturn(category);

        CategoryAdminResponse categoryAdminResponse = adminServiceImplementation
                .updateCategory(
                        categoryRequest,
                        "category"
                );

        Assertions.assertThat(categoryAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Delete category exception test!")
    void AdminService_DeleteCategoryException_ThrowException() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "category"
                )
        ).thenReturn(Optional.ofNullable(category));

        Mockito.lenient().doNothing()
                .when(categoryRepository)
                .deleteByUrlIgnoreCase(
                        "category"
                );

        assertThrows(
                CategoryNotFoundException.class,
                () -> adminServiceImplementation.deleteCategory(
                        "category1"
                )
        );

    }

    @Test
    @DisplayName("Get all brands test!")
    void AdminService_GetAllBrands_ReturnPageBrandsAdminResponse() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.findAll(pageable)
        ).thenReturn(brandEntityPage);

        Page<BrandAdminResponse> brandAdminResponses = adminServiceImplementation
                .getAllBrands(pageable);

        Assertions.assertThat(brandAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get single brand test!")
    void AdminService_GetSingleBrand_ReturnBrandAdminResponse() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase(
                        "brand"
                )
        ).thenReturn(Optional.of(brand));

        BrandAdminResponse brandAdminResponse = adminServiceImplementation
                .getSingleBrand("brand");

        Assertions.assertThat(brandAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Get single brand throw exception test!")
    void AdminService_GetSingleBrand_ThrowException() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase(
                        "brand"
                )
        ).thenReturn(Optional.of(brand));

        assertThrows(
                BrandNotFoundException.class,
                ()-> adminServiceImplementation.getSingleBrand(
                        "brand1"
                )
        );

    }

    @Test
    @DisplayName("Get brands by category test!")
    void AdminService_GetBrandsByCategory_ReturnPageBrandAdminResponse() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.findBrandEntitiesByCategories_UrlIgnoreCase(
                        "category",
                        pageable
                )
        ).thenReturn(brandEntityPage);

        Page<BrandAdminResponse> brandAdminResponses = adminServiceImplementation
                .getSetOfBrandsByCategory(
                        "category",
                        pageable
                );

        Assertions.assertThat(brandAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Search brand test!")
    void AdminService_SearchBrand_ReturnPageBrandAdminResponse() {

        List<BrandEntity> brandEntityList = new ArrayList<>();
        brandEntityList.add(brand);

        Page<BrandEntity> brandEntityPage = new PageImpl<>(brandEntityList);

        Mockito.lenient().when(
                brandRepository.searchByNameIgnoreCase(
                        "brand",
                        pageable
                )
        ).thenReturn(brandEntityPage);

        Page<BrandAdminResponse> brandAdminResponses = adminServiceImplementation
                .searchBrands(
                        "brand",
                        pageable
                );

        Assertions.assertThat(brandAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Create brand test!")
    void AdminService_CreateBrand_ReturnBrandAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase(
                        "category"
                )
        ).thenReturn(Optional.of(category));

        Mockito.lenient().when(
                brandRepository.save(
                        Mockito.any(BrandEntity.class)
                )
        ).thenReturn(brand);

        BrandAdminResponse brandAdminResponse = adminServiceImplementation
                .createBrand(
                        brandRequest,
                        "category"
                );

        Assertions.assertThat(brandAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Update brand test!")
    void AdminService_UpdateBrand_ReturnBrandAdminResponse() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase("brand")
        ).thenReturn(Optional.of(brand));

        Mockito.lenient().when(
                brandRepository.save(brand)
        ).thenReturn(brand);

        BrandAdminResponse brandAdminResponse = adminServiceImplementation
                .updateBrand(
                        brandRequest,
                        "brand"
                );

        Assertions.assertThat(brandAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Delete brand throw exception test!")
    void AdminService_DeleteBrand_ThrowException() {

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase(
                        "brand"
                )
        ).thenReturn(Optional.of(brand));

        Mockito.lenient().doNothing()
                .when(brandRepository)
                .deleteByUrlIgnoreCase("brand");

        assertThrows(
                BrandNotFoundException.class,
                ()-> adminServiceImplementation.deleteBrand(
                        "brand1"
                )
        );

    }

    @Test
    @DisplayName("Get products test!")
    void AdminService_GetAllProducts_ReturnPageProductsAdminResponse() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findAll(pageable)
        ).thenReturn(productEntityPage);

        Page<ProductAdminResponse> productAdminResponses = adminServiceImplementation
                .getAllProducts(pageable);

        Assertions.assertThat(productAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get product details test!")
    void AdminService_GetProductsDetails_ReturnProductDetailsAdminResponse() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase("product")
        ).thenReturn(Optional.of(product));

        ProductDetailsAdminResponse productDetailsAdminResponse = adminServiceImplementation
                .getSingleProductDetails("product");

        Assertions.assertThat(productDetailsAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Get product details throw exception test!")
    void AdminService_GetProductsDetails_ThrowException() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase("product")
        ).thenReturn(Optional.of(product));

        assertThrows(
                ProductNotFoundException.class,
                ()-> adminServiceImplementation.getSingleProductDetails(
                        "product1"
                )
        );

    }

    @Test
    @DisplayName("Search products test!")
    void AdminService_SearchProducts_ReturnPageProductsAdminResponse() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.searchByNameIgnoreCase(
                        "product", pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductAdminResponse> productAdminResponses = adminServiceImplementation
                .searchProducts(
                        "product",
                        pageable
                );

        Assertions.assertThat(productAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get products by category test!")
    void AdminService_GetProductsByCategory_ReturnPageProductsAdminResponse() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findByCategoryUrl(
                        "category",
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductAdminResponse> productAdminResponses = adminServiceImplementation
                .getProductsByCategory(
                        "category",
                        pageable
                );

        Assertions.assertThat(productAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Get products by brand test!")
    void AdminService_GetProductsByBrand_ReturnPageProductsAdminResponse() {

        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntityList);

        Mockito.lenient().when(
                productRepository.findByBrandUrl(
                        "brand",
                        pageable
                )
        ).thenReturn(productEntityPage);

        Page<ProductAdminResponse> productAdminResponses = adminServiceImplementation
                .getProductsByBrand(
                        "brand",
                        pageable
                );

        Assertions.assertThat(productAdminResponses).isNotNull();

    }

    @Test
    @DisplayName("Create product test!")
    void AdminService_CreateProduct_ReturnProductAdminResponse() {

        Mockito.lenient().when(
                categoryRepository.findByUrlIgnoreCase("category")
        ).thenReturn(Optional.of(category));

        Mockito.lenient().when(
                brandRepository.findByUrlIgnoreCase("brand")
        ).thenReturn(Optional.of(brand));

        Mockito.lenient().when(
                productRepository.save(
                        Mockito.any(ProductEntity.class)
                )
        ).thenReturn(product);

        ProductAdminResponse productAdminResponse = adminServiceImplementation
                .createProduct(
                        productRequest,
                        "category",
                        "brand"
                );

        Assertions.assertThat(productAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Update product test!")
    void AdminService_UpdateProduct_ReturnProductAdminResponse() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase("product")
        ).thenReturn(Optional.of(product));

        Mockito.lenient().when(
                productRepository.save(product)
        ).thenReturn(product);

        ProductAdminResponse productAdminResponse = adminServiceImplementation
                .updateProduct(
                        productRequest,
                        "product"
                );

        Assertions.assertThat(productAdminResponse).isNotNull();

    }

    @Test
    @DisplayName("Delete product throw exception test!")
    void AdminService_DeleteProduct_ThrowException() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase("product")
        ).thenReturn(Optional.of(product));

        Mockito.lenient().doNothing()
                .when(productRepository)
                .deleteByUrlIgnoreCase("product");

        assertThrows(
                ProductNotFoundException.class,
                ()-> adminServiceImplementation.deleteProduct(
                        "product1"
                )
        );

    }

    @Test
    @DisplayName("Create product description test!")
    void AdminService_CreateProductDescription_ReturnProductInfoResponse() {

        Mockito.lenient().when(
                productRepository.findByUrlIgnoreCase("product")
        ).thenReturn(Optional.of(product));

        Mockito.lenient().when(
                productInfoRepository.save(
                        Mockito.any(ProductInfoEntity.class)
                )
        ).thenReturn(productInfo);

        ProductInfoResponse productInfoResponse = adminServiceImplementation
                .createProductDescription(
                        productInfoRequest,
                        "product"
                );

        Assertions.assertThat(productInfoResponse).isNotNull();

    }

    @Test
    @DisplayName("Delete product description throw exception test!")
    void AdminService_DeleteProductDescription() {

        Mockito.lenient().when(
                productInfoRepository.findByProductUrlIgnoreCase("product")
        ).thenReturn(Optional.of(productInfo));

        Mockito.lenient().doNothing()
                .when(productInfoRepository)
                .deleteByProductUrl("product");

        assertThrows(
                ProductNotFoundException.class,
                ()-> adminServiceImplementation.deleteProductDescription("product1")
        );

    }

}