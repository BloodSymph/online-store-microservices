package com.company.productservice.dto.product;

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
public class ProductAdminResponse {

    private Long id;

    @NotBlank(message = "Product name should not be empty!")
    @Length(min = 3, max = 40)
    private String name;

    @Length(min = 3, max = 20)
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

}
