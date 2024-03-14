package com.toy.mall.category.service.response;

import com.toy.mall.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private Long id;

    private String name;

    private Long parentId;

    private int depth;

    private List<CategoryResponse> child = new ArrayList<>();

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getParent() != null ? category.getParent().getId() : null,
                category.getDepth(),
                category.getChild()
                        .stream()
                        .map(CategoryResponse::of)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", depth=" + depth +
                '}';
    }
}
