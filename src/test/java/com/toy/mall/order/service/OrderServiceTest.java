package com.toy.mall.order.service;

import com.toy.mall.cart.service.cqrs.CartQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired CartQueryPort cartQueryPort;


}