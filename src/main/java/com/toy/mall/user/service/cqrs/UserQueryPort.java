package com.toy.mall.user.service.cqrs;

import com.toy.mall.user.domain.User;

import java.util.Optional;

public interface UserQueryPort {
    boolean existsByLoginId(String loginId);

    Optional<User> findById(Long id);

    Optional<User> findByLoginId(String testId);
}
