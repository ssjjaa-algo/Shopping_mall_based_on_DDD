package com.toy.mall.category.repository;

import com.toy.mall.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<Category,Long> {

    boolean existsByName(String name);

    Category findByName(String name);

    List<Category> findByParentIsNull();
}
