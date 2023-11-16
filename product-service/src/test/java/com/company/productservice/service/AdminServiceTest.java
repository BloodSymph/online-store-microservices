package com.company.productservice.service;

import com.company.productservice.dto.brand.BrandRequest;
import com.company.productservice.dto.category.CategoryAdminResponse;
import com.company.productservice.dto.category.CategoryRequest;
import com.company.productservice.dto.product.ProductRequest;
import com.company.productservice.dto.product_info.ProductInfoRequest;
import com.company.productservice.entity.BrandEntity;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.exception.CategoryNotFoundException;
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
import static org.junit.jupiter.api.Assertions.*;

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
    void createCategory() {



    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void getAllBrands() {
    }

    @Test
    void getSingleBrand() {
    }

    @Test
    void getSetOfBrandsByCategory() {
    }

    @Test
    void searchBrands() {
    }

    @Test
    void createBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void deleteBrand() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getSingleProduct() {
    }

    @Test
    void searchProducts() {
    }

    @Test
    void getProductsByCategory() {
    }

    @Test
    void getProductsByBrand() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void createProductDescription() {
    }

    @Test
    void updateProductDescription() {
    }

    @Test
    void deleteProductDescription() {
    }

}