package com.toy.mall.cart.service;

import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.cqrs.CartCommandPort;
import com.toy.mall.cart.service.cqrs.CartQueryPort;
import com.toy.mall.cart.service.request.AddProductToCartServiceRequest;
import com.toy.mall.cart.service.request.DeleteProductFromCartServiceRequest;
import com.toy.mall.cart.service.response.AllCartResponse;
import com.toy.mall.cart.service.response.CartInfoResponse;
import com.toy.mall.product.domain.Product;
import com.toy.mall.product.service.cqrs.ProductQueryPort;
import com.toy.mall.user.domain.User;
import com.toy.mall.user.service.cqrs.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductQueryPort productQueryPort;
    private final UserQueryPort userQueryPort;
    private final CartCommandPort cartCommandPort;
    private final CartQueryPort cartQueryPort;

    @Transactional
    public void add(AddProductToCartServiceRequest serviceRequest) {

        User user = userQueryPort.findByLoginId(serviceRequest.getLoginId())
                .orElseThrow(() -> new IllegalStateException("유저가 존재하지 않습니다."));

        Product product = productQueryPort.findById(serviceRequest.getProductId())
                .orElseThrow(() -> new IllegalStateException("해당하는 상품이 없습니다."));

        product.canAddToCart(serviceRequest.getQuantity());
        Cart cart = Cart.createCart(user, product, serviceRequest.getQuantity());

        cartCommandPort.save(cart);
    }

    @Transactional
    public void delete(DeleteProductFromCartServiceRequest serviceRequest) {

        User user = userQueryPort.findByLoginId(serviceRequest.getLoginId())
                .orElseThrow(() -> new IllegalStateException("유저가 존재하지 않습니다."));

        cartCommandPort.deleteByUserAndIdIn(user.getId(), serviceRequest.getIds());
    }

    @Transactional(readOnly = true)
    public AllCartResponse getCarts(String loginId) {

        User user = userQueryPort.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalStateException("유저가 존재하지 않습니다."));

        List<CartInfoResponse> carts = cartQueryPort.findByUser(user.getId());

        return new AllCartResponse(carts);
    }
}
