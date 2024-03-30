package com.toy.mall.order.service;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.cqrs.CartQueryPort;
import com.toy.mall.order.service.request.OrderCreateServiceRequest;
import com.toy.mall.user.domain.User;
import com.toy.mall.user.service.cqrs.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserQueryPort userQueryPort;
    private final CartQueryPort cartQueryPort;

    @Transactional
    public void create(String loginId, OrderCreateServiceRequest serviceRequest) {

        User user = userQueryPort.findByLoginId(loginId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 회원"));

        List<Cart> purchases = cartQueryPort.findByUserIdAndCartIdIn(user.getId(), serviceRequest.getCartIds());

        // 상품들의 재고를 확인하며 구매가 가능한지 체크해야 한다.



    }
}
