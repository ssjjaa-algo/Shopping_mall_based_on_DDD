package com.toy.mall.order.controller.request;

import com.toy.mall.order.service.request.OrderCreateServiceRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    private String receiverName;
    private String receiverPhone;
    private String city;
    private String street;
    private String zipcode;
    private String detailedAddress;
    private List<Long> cartIds;

    public OrderCreateRequest(String receiverName, String receiverPhone, String city, String street, String zipcode, String detailedAddress, List<Long> cartIds) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detailedAddress = detailedAddress;
        this.cartIds = cartIds;
    }

    public OrderCreateServiceRequest toServiceRequest() {

        return new OrderCreateServiceRequest(receiverName, receiverPhone, city, street,
                zipcode, detailedAddress, cartIds);
    }
}
