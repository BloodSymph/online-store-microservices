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

    private Long version;

    private Long id;

    @Length(min = 6, max = 50)
    private String title;

    @Length(min = 12, max = 256)
    private String description;

    @Length(min = 3, max = 30)
    private String series;

    @Length(min = 3, max = 30)
    private String height;

    @Length(min = 3, max = 30)
    private String width;

    @Length(min = 3, max = 30)
    private String weight;

    @Length(min = 3, max = 30)
    private String os;

    @Length(min = 3, max = 30)
    private String display;

    @Length(min = 3, max = 30)
    private String resolution;

    @Length(min = 3, max = 30)
    private String cpu;

    @Length(min = 3, max = 30)
    private String graphicCard;

    @Length(min = 3, max = 30)
    private String gpu;

    @Length(min = 3, max = 30)
    private String ramType;

    @Length(min = 3, max = 30)
    private String ram;

    @Length(min = 3, max = 30)
    private String memoryType;

    @Length(min = 3, max = 30)
    private String memory;

}
