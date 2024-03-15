package com.toy.mall.product.service.cqrs;

import com.toy.mall.product.domain.Product;
import com.toy.mall.product.service.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductQueryPort {
    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    Page<ProductResponse> findDistinctByCategories_Category_IdIn(List<Long> categoriesId, Pageable pageable);

    Optional<Product> findById(Long id);
}
