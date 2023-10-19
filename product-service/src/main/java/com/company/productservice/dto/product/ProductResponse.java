package com.company.productservice.dto.product;

import com.company.productservice.dto.brand.BrandResponse;
import com.company.productservice.dto.category.CategoryResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    @NotBlank(message = "Product name should not be empty!")
    @Length(min = 3, max = 40)
    private String name;

    @NotNull
    @NotBlank(message = "Product price should not be empty!")
    @Length(min = 3, max = 40)
    private String price;

    @URL
    @NotNull
    @NotBlank(message = "Product photo should not be empty!")
    private String photoUrl;

}
