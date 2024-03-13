package com.toy.mall.user.service;

import com.toy.mall.user.domain.User;
import com.toy.mall.user.service.cqrs.UserCommandPort;
import com.toy.mall.user.service.cqrs.UserQueryPort;
import com.toy.mall.user.service.request.UserServiceRegistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    public void regist(UserServiceRegistRequest serviceRequest) {

        if (userQueryPort.existsByLoginId(serviceRequest.getLoginId())) {
            throw new IllegalStateException("이미 존재하는 회원");
        }

        userCommandPort.save(new User(serviceRequest.getLoginId(),
                serviceRequest.getPhoneNumber(),
                serviceRequest.getAddress()));

    }
}
