package com.toy.mall.order.controller.request;

import com.toy.mall.order.domain.ShippingInfo;
import com.toy.mall.order.service.request.OrderCreateServiceRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    private ShippingInfo shippingInfo;
    private List<Long> cartIds;

    public OrderCreateRequest(ShippingInfo shippingInfo, List<Long> cartIds) {
        this.shippingInfo = shippingInfo;
        this.cartIds = cartIds;
    }

    public OrderCreateServiceRequest toServiceRequest() {

        return new OrderCreateServiceRequest(shippingInfo, cartIds);
    }
}
