package com.company.productservice.dto.category;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.dto.product.ProductResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAdminDetailResponse {

    private Long id;

    @Length(min = 3, max = 30)
    @NotBlank(message = "Category name should not be empty!")
    private String name;

    @Length(min = 3, max = 20)
    @NotNull
    @NotBlank(message = "Category slug should not be empty!")
    private String url;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime updatedAt;

    private Set<BrandResponse> brands;

    private Set<ProductResponse> products;

}
