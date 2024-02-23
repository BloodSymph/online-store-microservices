package com.company.cartservice.service.implementation;

import com.company.cartservice.client.ProductClient;
import com.company.cartservice.dto.CartResponse;
import com.company.cartservice.dto.ProductResponse;
import com.company.cartservice.entity.CartEntity;
import com.company.cartservice.repository.CartRepository;
import com.company.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"Cart"})
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepository;

    private ProductClient productClient;

    @Override
    @Transactional
    @CachePut(key = "", unless = "#result == null ")
    public CartResponse addProductToTheCart(Long productId, String username) {

        CartEntity cart = new CartEntity();
        cart.setUsername(username);

        ProductResponse productResponse = productClient
                .getProductForAddToTheCart(productId);

        List<ProductResponse> products = new ArrayList<>();

        products.add(productResponse);

        cartRepository.save(cart);

        return CartResponse.builder()
                .id(cart.getId())
                .products(products)
                .build();

    }

}
