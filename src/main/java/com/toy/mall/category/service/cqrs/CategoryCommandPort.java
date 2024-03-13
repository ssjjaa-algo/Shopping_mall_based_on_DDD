package com.toy.mall.category.service.cqrs;

import com.toy.mall.category.domain.Category;

public interface CategoryCommandPort {
    void save(Category category);
}
