package com.toy.mall.cart.service.request;

import lombok.Getter;

@Getter
public class AddProductToCartServiceRequest {

    private Long productId;
    private String loginId;
    private int quantity;

    public AddProductToCartServiceRequest(Long productId, String loginId, int quantity) {
        this.productId = productId;
        this.loginId = loginId;
        this.quantity = quantity;
    }
}
