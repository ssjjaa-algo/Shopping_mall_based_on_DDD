package com.toy.mall.user.service.request;

import com.toy.mall.user.domain.Address;
import lombok.Getter;

@Getter
public class UserServiceAddressRequest {

    private String loginId;
    private Address address;
    public UserServiceAddressRequest(String loginId, Address address) {
        this.loginId = loginId;
        this.address = address;
    }
}
