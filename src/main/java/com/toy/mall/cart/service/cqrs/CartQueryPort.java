package com.toy.mall.cart.service.cqrs;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.response.CartInfoResponse;

import java.util.List;

public interface CartQueryPort {

    List<Cart> findByUserId(Long userId);

    List<CartInfoResponse> findByUser(Long id);

    List<Cart> findByUserIdAndCartIdIn(Long id, List<Long> cartIds);
}
