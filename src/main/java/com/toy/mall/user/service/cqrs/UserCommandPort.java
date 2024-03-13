package com.toy.mall.user.service.cqrs;

import com.toy.mall.user.domain.User;

public interface UserCommandPort {
    void save(User user);
}
