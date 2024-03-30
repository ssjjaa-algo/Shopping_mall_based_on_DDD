package com.toy.mall.order.repository;

import com.toy.mall.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public void save(Order order) {
        orderJpaRepository.save(order);
    }
}
