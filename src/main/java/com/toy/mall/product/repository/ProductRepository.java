package com.toy.mall.product.repository;

import com.toy.mall.product.domain.Product;
import com.toy.mall.product.service.cqrs.ProductCommandPort;
import com.toy.mall.product.service.cqrs.ProductQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepository implements ProductCommandPort, ProductQueryPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }
}
