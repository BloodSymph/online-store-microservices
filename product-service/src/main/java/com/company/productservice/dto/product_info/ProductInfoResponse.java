package com.company.productservice.dto.product_info;

import jakarta.persistence.Column;
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
public class ProductInfoResponse {

    private Long id;

    @NotBlank(message = "Product title should not be empty")
    @Length(min = 6, max = 50)
    private String title;

    @NotBlank(message = "Product series should not be empty")
    @Length(min = 3, max = 30)
    private String series;

    @NotBlank(message = "Product height should not be empty")
    @Length(min = 3, max = 30)
    private String height;

    @NotBlank(message = "Product width should not be empty")
    @Length(min = 3, max = 30)
    private String width;

    @NotBlank(message = "Product weight should not be empty")
    @Length(min = 3, max = 30)
    private String weight;

    @NotBlank(message = "Product os should not be empty")
    @Length(min = 3, max = 30)
    private String os;

    @NotBlank(message = "Product display should not be empty")
    @Length(min = 3, max = 30)
    private String display;

    @NotBlank(message = "Product resolution should not be empty")
    @Length(min = 3, max = 30)
    private String resolution;

    @NotBlank(message = "Product cpu should not be empty")
    @Length(min = 3, max = 30)
    private String cpu;

    @NotBlank(message = "Product graphic card should not be empty")
    @Length(min = 3, max = 30)
    private String graphicCard;

    @NotBlank(message = "Product gpu should not be empty")
    @Length(min = 3, max = 30)
    private String gpu;

    @NotBlank(message = "Product ram type should not be empty")
    @Length(min = 3, max = 30)
    private String ramType;

    @NotBlank(message = "Product ram should not be empty")
    @Length(min = 3, max = 30)
    private String ram;

    @NotBlank(message = "Product memory type should not be empty")
    @Length(min = 3, max = 30)
    private String memoryType;

    @NotBlank(message = "Product memory should not be empty")
    @Length(min = 3, max = 30)
    private String memory;

}
