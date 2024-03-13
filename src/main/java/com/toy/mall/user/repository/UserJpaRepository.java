package com.toy.mall.user.repository;

import com.toy.mall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);
    Optional<User> findByLoginId(String loginId);
}
