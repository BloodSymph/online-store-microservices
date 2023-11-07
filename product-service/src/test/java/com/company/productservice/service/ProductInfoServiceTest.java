package com.company.productservice.service;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import com.company.productservice.entity.ProductEntity;
import com.company.productservice.entity.ProductInfoEntity;
import com.company.productservice.repository.ProductInfoRepository;
import com.company.productservice.service.implementation.ProductInfoServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductInfoServiceTest {

    @Mock
    private ProductInfoRepository productInfoRepository;

    @InjectMocks
    private ProductInfoServiceImplementation productInfoServiceImplementation;

    private ProductInfoEntity productInfo;

    @BeforeEach
    void setUp() {

        productInfo = ProductInfoEntity.builder()
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

    }

    @Test
    @DisplayName("Get information about product test!")
    void ProductInfoService_GetInformationAboutProduct_ReturnProductInfoEntity() {

        Mockito.lenient().when(
                productInfoRepository.findByProductUrlIgnoreCase(
                        "product"
                )
        ).thenReturn(Optional.of(productInfo));

        ProductInfoResponse productInfoResponse = productInfoServiceImplementation
                .getProductInformation(
                        "product"
                );

        Assertions.assertThat(productInfoResponse).isNotNull();

    }

}