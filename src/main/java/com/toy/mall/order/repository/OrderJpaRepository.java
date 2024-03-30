package com.toy.mall.order.repository;

import com.toy.mall.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
