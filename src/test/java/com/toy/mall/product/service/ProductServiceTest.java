package com.toy.mall.product.service;

import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.CategoryService;
import com.toy.mall.product.controller.request.ProductCreateRequest;
import com.toy.mall.product.domain.Product;
import com.toy.mall.product.repository.ProductRepository;
import com.toy.mall.product.service.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductServiceTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;
    @Test
    @DisplayName("상품 등록 성공")
    public void createProduct() {
        //given
        ProductCreateRequest request = new ProductCreateRequest("등록","knit",3000,30);
        productService.create(request.toServiceRequest());

        //when
        Product product = productRepository.findByName("등록").orElseThrow(() ->
                new IllegalStateException("해당 상품을 찾을 수 없음"));

        //then
        assertThat(product.getName()).isEqualTo("등록");
    }

    @Test
    @DisplayName("최하위 카테고리가 아닌 상품 등록 실패")
    public void createProductNotLowestCategory() {
        //given
        ProductCreateRequest request = new ProductCreateRequest("등록","cloth",3000,30);

        //when & then
        assertThrows(IllegalStateException.class, () -> productService.create(request.toServiceRequest()));
    }

    @Test
    @DisplayName("동일한 상품 이름 등록 실패")
    public void createDuplicateProduct() {
        //given
        ProductCreateRequest request1 = new ProductCreateRequest("등록","knit",3000,30);
        //when & then
        productService.create(request1.toServiceRequest());
        assertThrows(IllegalStateException.class, () -> productService.create(request1.toServiceRequest()));
    }

    @Test
    @DisplayName("특정 카테고리에 해당하는 상품 찾기")
    public void productsByCategory() {
        //given
        //when

        Page<ProductResponse> products = productService.getProducts("cloth", 0);
        //then

        List<ProductResponse> contents = products.getContent();
        long total = products.getTotalElements();

        assertThat(contents.size()).isEqualTo(5);
        assertThat(total).isEqualTo(5);

    }


}