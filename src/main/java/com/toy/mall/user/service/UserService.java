package com.toy.mall.user.service;

import com.toy.mall.user.domain.User;
import com.toy.mall.user.service.cqrs.UserCommandPort;
import com.toy.mall.user.service.cqrs.UserQueryPort;
import com.toy.mall.user.service.request.UserServiceAddressRequest;
import com.toy.mall.user.service.request.UserServiceRegistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    @Transactional
    public void regist(UserServiceRegistRequest serviceRequest) {

        if (userQueryPort.existsByLoginId(serviceRequest.getLoginId())) {
            throw new IllegalStateException("이미 존재하는 회원");
        }

        userCommandPort.save(new User(serviceRequest.getLoginId(),
                serviceRequest.getPhoneNumber(),
                serviceRequest.getAddress()));

    }

    @Transactional
    public void changeAddress(UserServiceAddressRequest serviceRequest) {

        User user = userQueryPort.findByLoginId(serviceRequest.getLoginId())
                .orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않음"));

        user.changeAddress(serviceRequest.getAddress());
        userCommandPort.save(user);
    }
}
