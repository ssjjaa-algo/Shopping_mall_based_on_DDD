package com.toy.mall.cart.service.cqrs;

import com.toy.mall.cart.domain.Cart;

import java.util.List;

public interface CartCommandPort {
    void save(Cart cart);

    void deleteByIdIn(List<Long> ids);
}
