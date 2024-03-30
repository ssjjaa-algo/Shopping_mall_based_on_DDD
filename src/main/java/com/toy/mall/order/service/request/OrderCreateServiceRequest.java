package com.toy.mall.order.service.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateServiceRequest {

    private String receiverName;
    private String receiverPhone;
    private String city;
    private String street;
    private String zipcode;
    private String detailedAddress;
    private List<Long> cartIds;

    public OrderCreateServiceRequest(String receiverName, String receiverPhone, String city, String street, String zipcode, String detailedAddress, List<Long> cartIds) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detailedAddress = detailedAddress;
        this.cartIds = cartIds;
    }
}
