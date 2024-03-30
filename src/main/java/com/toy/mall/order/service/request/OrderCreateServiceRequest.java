package com.toy.mall.order.service.request;

import com.toy.mall.order.domain.ShippingInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateServiceRequest {

    private ShippingInfo shippingInfo;
    private List<Long> cartIds;

    public OrderCreateServiceRequest(ShippingInfo shippingInfo, List<Long> cartIds) {
        this.shippingInfo = shippingInfo;
        this.cartIds = cartIds;
    }
}
