package com.company.productservice.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandAdminResponse {

    private Long id;

    @Length(min = 3, max = 30)
    @NotBlank(message = "Brand name should not be empty!")
    private String name;

    @Length(min = 3, max = 20)
    @NotNull
    @NotBlank(message = "Brand slug should not be empty!")
    private String url;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime updatedAt;

}
