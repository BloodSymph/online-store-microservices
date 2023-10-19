package com.company.productservice.dto.category;

import com.company.productservice.dto.brand.BrandResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBrandsResponse {

    private Long id;

    @Length(min = 3, max = 30)
    @NotBlank(message = "Category name should not be empty!")
    private String name;

    private Set<BrandResponse> brands;

}
