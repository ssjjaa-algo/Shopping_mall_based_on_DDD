package com.toy.mall.cart.service.request;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteProductFromCartServiceRequest {

    private List<Long> ids;
    private String loginId;

    public DeleteProductFromCartServiceRequest(List<Long> ids, String loginId) {
        this.ids = ids;
        this.loginId = loginId;
    }
}
