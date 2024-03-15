package com.toy.mall.cart.service;

import com.toy.mall.cart.controller.request.AddProductToCartRequest;
import com.toy.mall.cart.domain.Cart;
import com.toy.mall.cart.repository.CartRepository;
import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.CategoryService;
import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import com.toy.mall.product.controller.request.ProductCreateRequest;
import com.toy.mall.product.repository.ProductRepository;
import com.toy.mall.product.service.ProductService;
import com.toy.mall.user.domain.Address;
import com.toy.mall.user.service.UserService;
import com.toy.mall.user.service.request.UserServiceRegistRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
        //given
        categoryDummyData();
        productDummyData();
        userService.regist(getUserServiceRegistRequest());
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

        categoryDummyData();
        productDummyData();
        userService.regist(getUserServiceRegistRequest());
        //when

        AddProductToCartRequest r = new AddProductToCartRequest(1L, "testId",31);

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> cartService.add(r.toServiceRequest()));
    }

    private void productDummyData() {

        ProductCreateRequest p1 = new ProductCreateRequest("니트1","knit",3000,30);
        ProductCreateRequest p2 = new ProductCreateRequest("니트2","knit",3000,30);
        ProductCreateRequest p3 = new ProductCreateRequest("맨1","mantoman",3000,30);
        ProductCreateRequest p4 = new ProductCreateRequest("맨2","mantoman",3000,30);
        ProductCreateRequest p5 = new ProductCreateRequest("바지1","bottom",3000,30);
        ProductCreateRequest p6 = new ProductCreateRequest("차1","car",3000,30);

        List<ProductCreateRequest> given = List.of(p1, p2, p3, p4, p5, p6);

        for (ProductCreateRequest request : given) {
            productService.create(request.toServiceRequest());
        }
    }

    private void categoryDummyData() {
        CategoryServiceCreateRequest r1 = new CategoryServiceCreateRequest(null, "cloth");
        CategoryServiceCreateRequest r2 = new CategoryServiceCreateRequest(1L, "top");
        CategoryServiceCreateRequest r3 = new CategoryServiceCreateRequest(1L, "bottom");
        CategoryServiceCreateRequest r4 = new CategoryServiceCreateRequest(2L, "knit");
        CategoryServiceCreateRequest r5 = new CategoryServiceCreateRequest(2L, "mantoman");
        CategoryServiceCreateRequest r6 = new CategoryServiceCreateRequest(null, "car");

        List<CategoryServiceCreateRequest> given = List.of(r1,r2,r3,r4,r5,r6);
        //when

        for (CategoryServiceCreateRequest request : given) {
            categoryService.create(request);
        }
    }

    private UserServiceRegistRequest getUserServiceRegistRequest() {
        String loginId = "testId";
        String phoneNumber = "010-1234-5678";
        String city = "서울시";
        String street = "강남구";
        String zipcode = "강남대로 1234길";
        String detailedAddress = "강남아파트 999동 999호";

        return new UserServiceRegistRequest(
                loginId, phoneNumber,
                new Address(city, street, zipcode, detailedAddress));
    }
}