package com.toy.mall.user.service.request;

import com.toy.mall.user.domain.Address;
import lombok.Getter;

@Getter
public class UserServiceRegistRequest {

    private String loginId;
    private String phoneNumber;
    private Address address;

    public UserServiceRegistRequest(String loginId, String phoneNumber, Address address) {
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
