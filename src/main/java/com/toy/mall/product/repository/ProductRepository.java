package com.toy.mall.product.repository;

import com.toy.mall.product.domain.Product;
import com.toy.mall.product.service.cqrs.ProductCommandPort;
import com.toy.mall.product.service.cqrs.ProductQueryPort;
import com.toy.mall.product.service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepository implements ProductCommandPort, ProductQueryPort {

    private final ProductJpaRepository productJpaRepository;
    private final ProductQuerydslRepository productQuerydslRepository;

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

    @Override
    public Page<ProductResponse> findDistinctByCategories_Category_IdIn(List<Long> categoriesId, Pageable pageable) {
        return productQuerydslRepository.findDistinctByCategories_Category_IdIn(categoriesId, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }
}
