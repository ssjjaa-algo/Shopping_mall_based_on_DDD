package com.toy.mall.order.domain;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {

    private String receiverName;
    private String receiverPhone;
    private String city;
    private String street;
    private String zipcode;
    private String detailedAddress;

    protected ShippingInfo(String receiverName, String receiverPhone,
                        String city, String street,
                        String zipcode, String detailedAddress) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detailedAddress = detailedAddress;
    }
}
