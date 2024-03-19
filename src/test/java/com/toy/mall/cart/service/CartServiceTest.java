package com.toy.mall.cart.service;

import com.toy.mall.cart.controller.request.AddProductToCartRequest;
import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.repository.CartRepository;
import com.toy.mall.cart.service.request.DeleteProductFromCartServiceRequest;
import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.CategoryService;
import com.toy.mall.product.repository.ProductRepository;
import com.toy.mall.product.service.ProductService;
import com.toy.mall.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CartServiceTest {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartRepository cartRepository;
    @Test
    @DisplayName("유저 카트에 상품을 추가")
    public void addCart() {

        //when

        AddProductToCartRequest r = new AddProductToCartRequest(1L, "testId",2);
        AddProductToCartRequest r1 = new AddProductToCartRequest(2L, "testId",17);
        cartService.add(r.toServiceRequest());
        cartService.add(r1.toServiceRequest());

        //then
        List<Cart> carts = cartRepository.findByUserId(1L);

        assertThat(carts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("재고보다 많은 상품 장바구니 담기 실패")
    public void addProductMoreThanProductStock() {
        //given

        //when
        AddProductToCartRequest r = new AddProductToCartRequest(1L, "testId",31);

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> cartService.add(r.toServiceRequest()));
    }

    @Test
    @DisplayName("장바구니 상품 삭제")
    public void deleteCart() {
        //when

        AddProductToCartRequest r = new AddProductToCartRequest(1L, "testId",2);
        AddProductToCartRequest r1 = new AddProductToCartRequest(2L, "testId",17);
        cartService.add(r.toServiceRequest());
        cartService.add(r1.toServiceRequest());

        List<Cart> all = cartRepository.findByUserId(1L);
        List<Long> cartIds = all.stream()
                        .map(Cart::getId)
                        .toList();

        cartService.delete(new DeleteProductFromCartServiceRequest(cartIds, "testId"));
        //then

        List<Cart> carts = cartRepository.findByUserId(1L);

        for (Cart cart : carts) {
            System.out.println(cart.getProduct().getId());
        }

        assertThat(carts.size()).isEqualTo(0);
    }
}