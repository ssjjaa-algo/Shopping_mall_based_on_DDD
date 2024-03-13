package com.toy.mall.user.repository;

import com.toy.mall.user.domain.User;
import com.toy.mall.user.service.cqrs.UserCommandPort;
import com.toy.mall.user.service.cqrs.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository implements UserCommandPort, UserQueryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByLoginId(String loginId) {
        return userJpaRepository.existsByLoginId(loginId);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(loginId);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
