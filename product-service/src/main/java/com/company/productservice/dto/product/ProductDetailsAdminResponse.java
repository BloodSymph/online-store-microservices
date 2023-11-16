package com.company.productservice.dto.product;

import com.company.productservice.dto.product_info.ProductInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDetailsAdminResponse extends ProductAdminResponse {

    private ProductInfoResponse productInfoResponse;

}
