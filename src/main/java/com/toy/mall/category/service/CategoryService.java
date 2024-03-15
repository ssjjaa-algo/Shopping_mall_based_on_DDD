package com.toy.mall.category.service;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.service.cqrs.CategoryCommandPort;
import com.toy.mall.category.service.cqrs.CategoryQueryPort;
import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import com.toy.mall.category.service.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryCommandPort categoryCommandPort;
    private final CategoryQueryPort categoryQueryPort;

    @Transactional
    public void create(CategoryServiceCreateRequest serviceRequest) {

        if (categoryQueryPort.existsByName(serviceRequest.getName())) {
            throw new IllegalStateException("등록하려는 카테고리의 이름이 이미 존재");
        }
        Category parent = existOrNull(serviceRequest.getParentId());
        Category category = Category.createCategory(serviceRequest.getName(), parent);

        categoryCommandPort.save(category);
    }

    private Category existOrNull(Long parentId) {

        return parentId == null ? null : categoryQueryPort.findById(parentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 부모 카테고리 값 입력"));

    }

    public List<CategoryResponse> getAllCategories() {

        List<Category> roots = categoryQueryPort.findByParentIsNull();

        return roots.stream()
                .map(CategoryResponse::of)
                .collect(toList());
    }
}
