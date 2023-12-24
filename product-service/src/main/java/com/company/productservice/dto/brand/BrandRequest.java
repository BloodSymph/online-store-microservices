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
public class BrandRequest {

    private Long version;

    @Length(min = 3, max = 30, message = "Name should be minimum of {min} and maximum of {max} characters!")
    @NotBlank(message = "Brand name should not be empty!")
    private String name;

    @Length(min = 3, max = 20, message = "Url should be minimum of {min} and maximum of {max} characters!")
    private String url;

}
