package com.toy.mall.product.service.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.toy.mall.product.service.response.QProductResponse is a Querydsl Projection type for ProductResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductResponse extends ConstructorExpression<ProductResponse> {

    private static final long serialVersionUID = -1278063720L;

    public QProductResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price) {
        super(ProductResponse.class, new Class<?>[]{long.class, String.class, int.class}, id, name, price);
    }

}

