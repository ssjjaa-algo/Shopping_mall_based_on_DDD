package com.toy.mall.category.repository;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.service.cqrs.CategoryCommandPort;
import com.toy.mall.category.service.cqrs.CategoryQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryRepository implements CategoryCommandPort, CategoryQueryPort {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findById(Long parentId) {
        return categoryJpaRepository.findById(parentId);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryJpaRepository.existsByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryJpaRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }
}
