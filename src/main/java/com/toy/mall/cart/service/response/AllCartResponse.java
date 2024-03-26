package com.toy.mall.cart.service.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AllCartResponse {

    private List<CartInfoResponse> carts = new ArrayList<>();
    private int amount;

    public AllCartResponse(List<CartInfoResponse> carts) {
        this.carts = carts;
        this.amount = carts.stream()
                .mapToInt(CartInfoResponse::getAmount)
                .sum();
    }
}
