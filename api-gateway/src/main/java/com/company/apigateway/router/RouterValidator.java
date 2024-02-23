package com.company.apigateway.router;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(

            "/api/v1/auth-service/client/signup",
            "/api/v1/auth-service/client/login",

            "/api/v1/product-service/client/categories",
            "/api/v1/product-service/client/categories/{categoryUrl}/details",
            "/api/v1/product-service/client/categories/search",
            "/api/v1/product-service/client/categories/{categoryUrl}/products",
            "/api/v1/product-service/client/categories/{categoryUrl}/brands",

            "/api/v1/product-service/client/brands",
            "/api/v1/product-service/client/brands/{brandUrl}/details",
            "/api/v1/product-service/client/brands/search",
            "/api/v1/product-service/client/brands/{brandUrl}/categories",
            "/api/v1/product-service/client/brands/{brandUrl}/products",

            "/api/v1/product-service/client/products",
            "/api/v1/product-service/client/products/{productId}/get",
            "/api/v1/product-service/client/products/products/search",
            "/api/v1/product-service/client/products/{productUrl}/details"

    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            openApiEndpoints.stream().noneMatch(
                    uri -> serverHttpRequest.getURI()
                            .getPath()
                            .contains(uri)
            );

}
