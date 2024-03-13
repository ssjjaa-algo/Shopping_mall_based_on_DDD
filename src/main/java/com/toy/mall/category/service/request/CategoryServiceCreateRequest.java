package com.toy.mall.category.service.request;

import lombok.Getter;

@Getter
public class CategoryServiceCreateRequest {

    private Long parentId;
    private String name;
    public CategoryServiceCreateRequest(Long parentId, String name) {

        this.parentId = parentId;
        this.name = name;
    }
}
