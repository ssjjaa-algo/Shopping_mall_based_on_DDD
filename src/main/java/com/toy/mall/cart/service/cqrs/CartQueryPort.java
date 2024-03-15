package com.toy.mall.cart.service.cqrs;

import com.toy.mall.cart.domain.Cart;

import java.util.List;

public interface CartQueryPort {

    List<Cart> findByUserId(Long userId);
}
