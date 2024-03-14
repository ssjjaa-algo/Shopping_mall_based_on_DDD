package com.toy.mall.product.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.mall.product.service.response.ProductResponse;
import com.toy.mall.product.service.response.QProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.mall.product.domain.QProduct.product;
import static com.toy.mall.product.domain.QProductCategory.productCategory;

@Repository
@RequiredArgsConstructor
public class ProductQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ProductResponse> findDistinctByCategories_Category_IdIn(List<Long> categoriesId, Pageable pageable) {

        List<ProductResponse> content = queryFactory
                .selectDistinct(new QProductResponse(
                        product.id,
                        product.name,
                        product.price
                ))
                .from(product)
                .leftJoin(product.categories, productCategory)
                .where(productCategory.category.id.in(categoriesId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(product.count())
                .from(product)
                .innerJoin(productCategory)
                .on(product.id.eq(productCategory.product.id))
                .where(productCategory.category.id.in(categoriesId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
