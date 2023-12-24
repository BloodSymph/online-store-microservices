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

    private Long version;

    @Length(min = 3, max = 30, message = "Name should be minimum of {min} and maximum of {max} characters!")
    @NotBlank(message = "Category name should not be empty!")
    private String name;

    @Length(min = 3, max = 20, message = "Url should be minimum of {min} and maximum of {max} characters!")
    private String url;

}
