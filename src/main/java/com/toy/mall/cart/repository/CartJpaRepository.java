package com.toy.mall.cart.repository;

import com.toy.mall.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);
}
