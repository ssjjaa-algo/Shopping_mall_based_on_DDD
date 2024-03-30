package com.toy.mall.cart.repository;

import com.toy.mall.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.id = :userId AND c.id IN :cartIds")
    void deleteByUserAndIdIn(@Param("userId") Long userId, @Param("cartIds") List<Long> cartIds);
}
