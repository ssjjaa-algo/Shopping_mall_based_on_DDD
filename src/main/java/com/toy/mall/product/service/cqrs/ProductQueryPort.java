package com.toy.mall.product.service.cqrs;

import com.toy.mall.product.domain.Product;

import java.util.Optional;

public interface ProductQueryPort {
    boolean existsByName(String name);

    Optional<Product> findByName(String name);
}
