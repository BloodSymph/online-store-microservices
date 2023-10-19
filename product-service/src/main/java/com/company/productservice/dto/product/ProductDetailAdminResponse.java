package com.company.productservice.dto.product;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.dto.product_info.ProductInfoResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailAdminResponse {

    private Long id;

    @NotBlank(message = "Product name should not be empty!")
    @Length(min = 3, max = 40)
    private String name;

    @Length(min = 3, max = 20)
    @NotNull
    @NotBlank(message = "Product slug should not be empty!")
    private String url;

    @NotNull
    @NotBlank(message = "Product price should not be empty!")
    @Length(min = 3, max = 40)
    private String price;

    @URL
    @NotNull
    @NotBlank(message = "Product photo should not be empty!")
    private String photoUrl;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime updatedAt;

    private CategoryResponse category;

    private BrandResponse brand;

    private ProductInfoResponse productInfo;

}
