package com.toy.mall.cart.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.service.response.CartInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.mall.cart.domain.QCart.cart;
import static com.toy.mall.user.domain.QUser.user;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class CartQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public List<CartInfoResponse> findByUser(Long id) {

        List<Cart> carts = queryFactory
                .select(cart)
                .from(cart)
                .leftJoin(cart.user, user).on(user.id.eq(id))
                .leftJoin(cart.product).fetchJoin()
                .fetch();

        return carts.stream()
                .map(c -> new CartInfoResponse(
                        c.getId(),
                        c.getProduct().getName(),
                        c.getProduct().getPrice(),
                        c.getQuantity()))
                .collect(toList());
    }
}
