package com.toy.mall.product.service.cqrs;

import com.toy.mall.product.domain.Product;

public interface ProductCommandPort {
    void save(Product product);
}
