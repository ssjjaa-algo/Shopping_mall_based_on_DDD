package com.toy.mall.user.controller.request;

import com.toy.mall.user.domain.Address;
import com.toy.mall.user.service.request.UserServiceAddressRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserAddressRequest {

    @NotBlank(message = "유저 Id가 누락")
    private String loginId;
    private String city;
    private String street;
    private String zipcode;
    private String detailedAddress;

    public UserServiceAddressRequest toServiceRequest() {

        Address address = new Address(city, street, zipcode, detailedAddress);

        return new UserServiceAddressRequest(loginId, address);
    }
}
