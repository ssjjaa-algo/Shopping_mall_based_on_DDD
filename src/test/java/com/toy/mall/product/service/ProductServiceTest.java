package com.toy.mall.product.service;

import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.CategoryService;
import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import com.toy.mall.product.controller.request.ProductCreateRequest;
import com.toy.mall.product.domain.Product;
import com.toy.mall.product.repository.ProductRepository;
import com.toy.mall.product.service.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
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
        categoryDummyData();
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
        categoryDummyData();
        ProductCreateRequest request = new ProductCreateRequest("등록","cloth",3000,30);

        //when & then
        assertThrows(IllegalStateException.class, () -> productService.create(request.toServiceRequest()));
    }

    @Test
    @DisplayName("동일한 상품 이름 등록 실패")
    public void createDuplicateProduct() {
        //given
        categoryDummyData();
        ProductCreateRequest request1 = new ProductCreateRequest("등록","knit",3000,30);
        //when & then
        productService.create(request1.toServiceRequest());
        assertThrows(IllegalStateException.class, () -> productService.create(request1.toServiceRequest()));
    }

    @Test
    @DisplayName("특정 카테고리에 해당하는 상품 찾기")
    public void productsByCategory() {
        //given
        categoryDummyData();
        productDummyData();
        //when

        Page<ProductResponse> products = productService.getProducts("cloth", 0);
        //then

        List<ProductResponse> contents = products.getContent();
        long total = products.getTotalElements();

        assertThat(contents.size()).isEqualTo(5);
        assertThat(total).isEqualTo(5);

    }

    private void productDummyData() {

        ProductCreateRequest p1 = new ProductCreateRequest("니트1","knit",3000,30);
        ProductCreateRequest p2 = new ProductCreateRequest("니트2","knit",3000,30);
        ProductCreateRequest p3 = new ProductCreateRequest("맨1","mantoman",3000,30);
        ProductCreateRequest p4 = new ProductCreateRequest("맨2","mantoman",3000,30);
        ProductCreateRequest p5 = new ProductCreateRequest("바지1","bottom",3000,30);
        ProductCreateRequest p6 = new ProductCreateRequest("차1","car",3000,30);

        List <ProductCreateRequest> given = List.of(p1, p2, p3, p4, p5, p6);

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

}