package com.toy.mall.category.service;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Slf4j
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 1개 생성 성공")
    public void createSuccess() {
        //given
        CategoryServiceCreateRequest request = new CategoryServiceCreateRequest(null, "cloth");
        //when
        categoryService.create(request);

        //then
        Category category = categoryRepository.findByName("cloth");
        log.info("name = {}, parent = {} ",category.getName(), category.getParent());

    }

    @Test
    @DisplayName("다수의 카테고리 생성 성공")
    public void createManyCategorySuccess() {

        //given
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

        //then
        Category category1 = categoryRepository.findByName("cloth");
        Category category2 = categoryRepository.findByName("top");
        Category category3 = categoryRepository.findByName("bottom");
        Category category4 = categoryRepository.findByName("knit");
        Category category5 = categoryRepository.findByName("mantoman");
        Category category6 = categoryRepository.findByName("car");

        List<Category> categories = List.of(category1, category2, category3, category4, category5, category6);

        for (Category c : categories) {
            log.info("name = {}, depth = {}, parent = {}",c.getName(), c.getDepth(), c.getParent());

            for (Category c1 : c.getChild()) {
                log.info("child = {}", c1.getName());
            }
        }

        assertThat(category1.getChild().size()).isEqualTo(2);
        assertThat(category2.getChild().size()).isEqualTo(2);
        assertThat(category3.getChild().size()).isEqualTo(0);

        assertThat(category2.getParent().getName()).isEqualTo("cloth");
        assertThat(category3.getParent().getName()).isEqualTo("cloth");
        assertThat(category4.getParent().getName()).isEqualTo("top");

        assertThat(category6.getParent()).isNull();

    }

    @Test
    @DisplayName("중복된 카테고리 이름 생성 실패")
    public void createDuplicateNameCategory() {
        //given
        CategoryServiceCreateRequest request1 = new CategoryServiceCreateRequest(null, "cloth");
        CategoryServiceCreateRequest request2 = new CategoryServiceCreateRequest(null, "cloth");
        //when
        categoryService.create(request1);

        //then
        assertThrows(IllegalStateException.class, () -> categoryService.create(request2));
    }

}