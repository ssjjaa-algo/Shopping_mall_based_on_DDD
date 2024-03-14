package com.toy.mall.product.controller;

import com.toy.mall.product.controller.request.ProductCreateRequest;
import com.toy.mall.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid ProductCreateRequest productCreateRequest) {

        productService.create(productCreateRequest.toServiceRequest());

        return ResponseEntity.ok("상품 생성 완료");
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getProducts(@PathVariable String categoryName, int pageNum) {

        return ResponseEntity.ok().body(productService.getProducts(categoryName, pageNum));
    }
}
