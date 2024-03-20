package com.toy.mall.cart.repository;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.cqrs.CartCommandPort;
import com.toy.mall.cart.service.cqrs.CartQueryPort;
import com.toy.mall.cart.service.response.CartInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartRepository implements CartQueryPort, CartCommandPort {

    private final CartJpaRepository cartJpaRepository;
    private final CartQuerydslRepository cartQuerydslRepository;

    @Override
    public void save(Cart cart) {
        cartJpaRepository.save(cart);
    }

    @Override
    public void deleteByUserAndIdIn(Long id, List<Long> ids) {
        cartJpaRepository.deleteByUserAndIdIn(id, ids);
    }

    @Override
    public List<CartInfoResponse> findByUser(Long id) {
        return cartQuerydslRepository.findByUser(id);
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartJpaRepository.findByUserId(userId);
    }
}
