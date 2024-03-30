package com.toy.mall.order.service;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.cqrs.CartQueryPort;
import com.toy.mall.order.domain.Order;
import com.toy.mall.order.domain.OrderProduct;
import com.toy.mall.order.service.request.OrderCreateServiceRequest;
import com.toy.mall.product.domain.Product;
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

        List<Cart> carts = cartQueryPort.findByUserIdAndCartIdIn(user.getId(), serviceRequest.getCartIds());

        Order order = Order.create(user, serviceRequest.getShippingInfo());
        int totalPrice = 0;
        for (Cart cart : carts) {
            Product product = cart.getProduct();
            product.deductStock(cart.getQuantity());
            OrderProduct orderProduct = OrderProduct.create(order, product, cart.getQuantity());
            totalPrice += product.getPrice() * cart.getQuantity();
        }

    }
}
