package com.toy.mall.user.controller.request;

import com.toy.mall.user.domain.Address;
import com.toy.mall.user.service.request.UserServiceRegistRequest;
import jakarta.validation.constraints.NotBlank;

public class UserRegistRequest {

    @NotBlank(message = "loginId 필수")
    private String loginId;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
    private String detailedAddress;

    public UserServiceRegistRequest toServiceRequest() {

        Address address = new Address(city, street, zipcode, detailedAddress);
        return new UserServiceRegistRequest(
                this.loginId,
                this.phoneNumber,
                address
        );

    }
}
