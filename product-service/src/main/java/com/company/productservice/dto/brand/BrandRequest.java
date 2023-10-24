package com.company.productservice.dto.brand;

import com.company.productservice.dto.category.CategoryRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BrandRequest {

    @Length(min = 3, max = 30)
    @NotBlank(message = "Brand name should not be empty!")
    private String name;

    @Length(min = 3, max = 20)
    @NotNull
    @NotBlank(message = "Brand slug should not be empty!")
    private String url;

}
