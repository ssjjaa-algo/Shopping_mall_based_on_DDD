package com.toy.mall.order.controller;

import com.toy.mall.order.controller.request.OrderCreateRequest;
import com.toy.mall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/orders")
    public ResponseEntity<?> create(@RequestParam String loginId, OrderCreateRequest orderCreateRequest) {

        orderService.create(loginId, orderCreateRequest.toServiceRequest());

        return ResponseEntity.ok("주문 완료");
    }

}
