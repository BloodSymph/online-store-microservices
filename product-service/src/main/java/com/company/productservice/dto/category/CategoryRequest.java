package com.company.productservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @Length(min = 3, max = 30)
    @NotBlank(message = "Category name should not be empty!")
    private String name;

    @Length(min = 3, max = 20)
    private String url;

}
