package com.toy.mall.product.repository;

import com.toy.mall.product.domain.Product;
import com.toy.mall.product.service.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    List<ProductResponse> findDistinctByCategories_Category_IdIn(List<Long> categoriesId);
}
