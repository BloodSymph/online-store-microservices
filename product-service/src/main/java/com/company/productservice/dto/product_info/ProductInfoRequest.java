package com.company.productservice.dto.product_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoRequest {

    private Long version;

    @Length(min = 6, max = 50, message = "Title should be minimum of {min} and maximum of {max} characters!")
    private String title;

    @Length(min = 12, max = 256, message = "Description should be minimum of {min} and maximum of {max} characters!")
    private String description;

    @Length(min = 3, max = 30, message = "Series should be minimum of {min} and maximum of {max} characters!")
    private String series;

    @Length(min = 3, max = 30, message = "Height should be minimum of {min} and maximum of {max} characters!")
    private String height;

    @Length(min = 3, max = 30, message = "Width should be minimum of {min} and maximum of {max} characters!")
    private String width;

    @Length(min = 3, max = 30, message = "Weight should be minimum of {min} and maximum of {max} characters!")
    private String weight;

    @Length(min = 3, max = 30, message = "Os should be minimum of {min} and maximum of {max} characters!")
    private String os;

    @Length(min = 3, max = 30, message = "Display should be minimum of {min} and maximum of {max} characters!")
    private String display;

    @Length(min = 3, max = 30, message = "Resolution should be minimum of {min} and maximum of {max} characters!")
    private String resolution;

    @Length(min = 3, max = 30, message = "Cpu should be minimum of {min} and maximum of {max} characters!")
    private String cpu;

    @Length(min = 3, max = 30, message = "Graphic card should be minimum of {min} and maximum of {max} characters!")
    private String graphicCard;

    @Length(min = 3, max = 30, message = "Gpu should be minimum of {min} and maximum of {max} characters!")
    private String gpu;

    @Length(min = 3, max = 30, message = "Ram type should be minimum of {min} and maximum of {max} characters!")
    private String ramType;

    @Length(min = 3, max = 30, message = "Ram should be minimum of {min} and maximum of {max} characters!")
    private String ram;

    @Length(min = 3, max = 30, message = "Memory type should be minimum of {min} and maximum of {max} characters!")
    private String memoryType;

    @Length(min = 3, max = 30, message = "Memory should be minimum of {min} and maximum of {max} characters!")
    private String memory;

}
