package com.toy.mall.cart.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GetCartsRequest {

    @NotBlank(message = "loginId 정보가 없음")
    String loginId;

    public GetCartsRequest(String loginId) {
        this.loginId = loginId;
    }
}
