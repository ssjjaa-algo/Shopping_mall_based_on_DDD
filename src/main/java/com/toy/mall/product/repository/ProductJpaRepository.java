package com.toy.mall.product.repository;

import com.toy.mall.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

}
