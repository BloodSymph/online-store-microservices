package com.company.productservice.dto.product;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDetailsResponse extends ProductResponse{

    private ProductInfoResponse productInfoResponse;

}
