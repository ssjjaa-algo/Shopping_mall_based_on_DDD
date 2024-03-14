package com.toy.mall.category.controller.request;

import com.toy.mall.category.service.request.CategoryServiceCreateRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryCreateRequest {

    private Long parentId;

    @NotBlank(message = "카테고리 이름은 필수")
    private String name;

    public CategoryCreateRequest(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    public CategoryServiceCreateRequest toServiceRequest() {

        return new CategoryServiceCreateRequest(parentId, name);
    }
}
