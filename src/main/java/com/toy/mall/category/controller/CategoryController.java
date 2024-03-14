package com.toy.mall.category.controller;

import com.toy.mall.category.controller.request.CategoryCreateRequest;
import com.toy.mall.category.service.CategoryService;
import com.toy.mall.category.service.response.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid CategoryCreateRequest categoryCreateRequest) {

        categoryService.create(categoryCreateRequest.toServiceRequest());

        return ResponseEntity.ok("카테고리 등록 완료");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> all() {

        return ResponseEntity.ok().body(categoryService.getAllCategories());

    }
}
