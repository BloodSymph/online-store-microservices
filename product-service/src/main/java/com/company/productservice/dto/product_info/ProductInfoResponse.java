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

    private String title;

    private String description;

    private String series;

    private String height;

    private String width;

    private String weight;

    private String os;

    private String display;

    private String resolution;

    private String cpu;

    private String graphicCard;

    private String gpu;

    private String ramType;

    private String ram;

    private String memoryType;

    private String memory;

}
