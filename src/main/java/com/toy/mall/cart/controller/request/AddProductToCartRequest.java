package com.toy.mall.cart.controller.request;

import com.toy.mall.cart.service.request.AddProductToCartServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AddProductToCartRequest {

    @NotBlank(message = "선택한 상품이 없음")
    private Long productId;

    @NotBlank(message = "id가 없는 회원")
    private String loginId;

    @Positive(message = "1개 이상만 장바구니에 담을 수 있음")
    private int quantity;

    public AddProductToCartRequest(Long productId, String loginId, int quantity) {
        this.productId = productId;
        this.loginId = loginId;
        this.quantity = quantity;
    }

    public AddProductToCartServiceRequest toServiceRequest() {
        return new AddProductToCartServiceRequest(productId, loginId, quantity);
    }
}
