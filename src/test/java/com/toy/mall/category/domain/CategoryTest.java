package com.toy.mall.category.domain;

import com.toy.mall.category.repository.CategoryRepository;
import com.toy.mall.category.service.CategoryService;
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
class CategoryTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("특정 카테고리의 최하위 자식들의 id를 출력")
    public void extractLowestCategoryIds() {

        //when
        Category c1 = categoryRepository.findByName("cloth");
        Category c2 = categoryRepository.findByName("top");

        List<Long> idsOfCloth = c1.extractLowestCategoryIds();
        List<Long> idsOfTop = c2.extractLowestCategoryIds();
        //then

        assertThat(idsOfCloth.size()).isEqualTo(3);
        assertThat(idsOfTop.size()).isEqualTo(2);
    }

}