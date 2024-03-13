package com.toy.mall.category.controller;

import com.toy.mall.category.controller.request.CategoryCreateRequest;
import com.toy.mall.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    public ResponseEntity<?> create(@Valid CategoryCreateRequest categoryCreateRequest) {

        categoryService.create(categoryCreateRequest.toServiceRequest());

        return ResponseEntity.ok("카테고리 등록 완료");
    }
}
