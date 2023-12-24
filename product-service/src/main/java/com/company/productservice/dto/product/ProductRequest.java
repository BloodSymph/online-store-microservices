package com.company.productservice.dto.product;

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
public class ProductRequest {

    private Long version;

    @NotBlank(message = "Product name should not be empty!")
    @Length(min = 3, max = 40, message = "Name should be minimum of {min} and maximum of {max} characters!")
    private String name;

    @Length(min = 3, max = 20, message = "Url should be minimum of {min} and maximum of {max} characters!")
    private String url;

    @NotNull
    @NotBlank(message = "Product price should not be empty!")
    @Length(min = 3, max = 40, message = "Price should be minimum of {min} and maximum of {max} characters!")
    private String price;

    @URL
    @NotNull
    @NotBlank(message = "Product photo should not be empty!")
    private String photoUrl;

}
