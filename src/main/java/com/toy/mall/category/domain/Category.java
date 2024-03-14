package com.toy.mall.category.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static java.util.stream.Collectors.toList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int depth;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> child = new ArrayList<>();

    private Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        this.depth = parent == null ? 1 : parent.getDepth() + 1;
    }

    // 연관관계 편의 메서드
    public void addChildCategory(Category category) {
        this.child.add(category);
        category.parent = this;
    }

    public List<Long> extractLowestCategoryIds () {

        if (this.getChild() == null || this.getChild().isEmpty()) {
            return List.of(this.getId());
        }

        return this.getChild()
                .stream()
                .map(Category::extractLowestCategoryIds)
                .flatMap(List::stream)
                .collect(toList());
    }

    public static Category createCategory(String name, Category parent) {

        Category category = new Category(name, parent);
        if (parent != null) {
            parent.addChildCategory(category);
        }
        return category;
    }


}
