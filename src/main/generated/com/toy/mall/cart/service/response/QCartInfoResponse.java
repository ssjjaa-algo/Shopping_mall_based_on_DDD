package com.toy.mall.cart.service.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.toy.mall.cart.service.response.QCartInfoResponse is a Querydsl Projection type for CartInfoResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCartInfoResponse extends ConstructorExpression<CartInfoResponse> {

    private static final long serialVersionUID = 726577490L;

    public QCartInfoResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity) {
        super(CartInfoResponse.class, new Class<?>[]{long.class, String.class, int.class, int.class}, id, productName, price, quantity);
    }

}

