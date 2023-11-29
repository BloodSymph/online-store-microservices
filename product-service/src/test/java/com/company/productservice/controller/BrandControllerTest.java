package com.company.productservice.controller;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = BrandController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    private BrandResponse brandResponse;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        brandResponse = BrandResponse.builder()
                .id(1L)
                .name("Brand")
                .build();

        pageable = PageRequest.of(
                0, 10,
                Sort.by(
                        Sort.Direction.ASC, "name"
                )
        );

    }


    @Test
    @DisplayName("Get brand by category test!")
    void BrandController_GetBrandsByCategory_ReturnPageBrandsResponse() throws Exception {

        List<BrandResponse> brandResponseList = new ArrayList<>();
        brandResponseList.add(brandResponse);

        Page<BrandResponse> brandResponsePage = new PageImpl<>(brandResponseList);

        Mockito.lenient().when(
                brandService.getBrandsByCategory(
                        "category", pageable
                )
        ).thenReturn(brandResponsePage);

        mockMvc.perform(
                get(
                        "/api/v1/product-service/client/categories/category/brands"
                )
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .param("sort", "name")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

        verify(
                brandService,
                times(1)
        ).getBrandsByCategory(
                "category", pageable
        );
    }

}