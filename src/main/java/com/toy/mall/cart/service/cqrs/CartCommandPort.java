package com.toy.mall.cart.service.cqrs;

import com.toy.mall.cart.domain.Cart;

public interface CartCommandPort {
    void save(Cart cart);
}
