package com.toy.mall.category.service.cqrs;

import com.toy.mall.category.domain.Category;

import java.util.Optional;

public interface CategoryQueryPort {
    Optional<Category> findById(Long parentId);

    boolean existsByName(String name);

    Category findByName(String cloth);
}
