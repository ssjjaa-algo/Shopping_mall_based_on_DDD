package com.toy.mall.product.service.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private int price;

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    @QueryProjection
    public ProductResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
