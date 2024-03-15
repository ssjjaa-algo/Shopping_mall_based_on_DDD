package com.toy.mall.cart.repository;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.cqrs.CartCommandPort;
import com.toy.mall.cart.service.cqrs.CartQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartRepository implements CartQueryPort, CartCommandPort {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public void save(Cart cart) {
        cartJpaRepository.save(cart);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        cartJpaRepository.deleteByIdIn(ids);
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartJpaRepository.findByUserId(userId);
    }
}
