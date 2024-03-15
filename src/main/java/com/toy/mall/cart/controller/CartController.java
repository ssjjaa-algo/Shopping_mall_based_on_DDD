package com.toy.mall.cart.controller;

import com.toy.mall.cart.controller.request.AddProductToCartRequest;
import com.toy.mall.cart.controller.request.DeleteProductFromCartRequest;
import com.toy.mall.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid AddProductToCartRequest addProductToCartRequest) {

        cartService.add(addProductToCartRequest.toServiceRequest());

        return ResponseEntity.ok("카트에 상품이 등록");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@Valid DeleteProductFromCartRequest deleteProductFromCartRequest) {

        cartService.delete(deleteProductFromCartRequest.toServiceRequest());
        return ResponseEntity.ok("카트에서 상품 삭제가 완료");
    }
}
