package com.company.productservice.dto.brand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {

    private Long id;

    @Length(min = 3, max = 30)
    @NotBlank(message = "Brand name should not be empty!")
    private String name;

}
