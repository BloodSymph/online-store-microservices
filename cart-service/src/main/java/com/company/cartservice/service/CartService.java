package com.company.cartservice.service;

import com.company.cartservice.dto.CartResponse;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    CartResponse addProductToTheCart(Long productId, String username);

}
