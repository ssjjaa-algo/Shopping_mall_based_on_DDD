package com.toy.mall.cart.controller.request;

import com.toy.mall.cart.service.request.DeleteProductFromCartServiceRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class DeleteProductFromCartRequest {

    private List<Long> ids;

    @NotBlank(message = "로그인된 정보가 없음")
    private String loginId;

    public DeleteProductFromCartRequest(List<Long> ids, String loginId) {
        this.ids = ids;
        this.loginId = loginId;
    }

    public DeleteProductFromCartServiceRequest toServiceRequest() {

        return new DeleteProductFromCartServiceRequest(ids, loginId);
    }
}
