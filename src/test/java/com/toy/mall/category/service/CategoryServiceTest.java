package com.toy.mall.category.service;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Slf4j
@ActiveProfiles("test")
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 1개 생성 성공")
    public void createSuccess() {
        //given
        CategoryServiceCreateRequest request = new CategoryServiceCreateRequest(null, "one");
        //when
        categoryService.create(request);

        //then
        Category category = categoryRepository.findByName("one");
        log.info("name = {}, parent = {} ",category.getName(), category.getParent());

    }


    @Test
    @DisplayName("중복된 카테고리 이름 생성 실패")
    public void createDuplicateNameCategory() {
        //given
        CategoryServiceCreateRequest request1 = new CategoryServiceCreateRequest(null, "duplicate");
        CategoryServiceCreateRequest request2 = new CategoryServiceCreateRequest(null, "duplicate");
        //when
        categoryService.create(request1);

        //then
        assertThrows(IllegalStateException.class, () -> categoryService.create(request2));
    }

}
