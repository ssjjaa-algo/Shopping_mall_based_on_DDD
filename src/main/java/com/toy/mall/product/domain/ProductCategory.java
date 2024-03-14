package com.toy.mall.product.domain;

import com.toy.mall.category.domain.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {

    @Id
    @GeneratedValue
    @Column(name = "product_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public static ProductCategory create(Product product, Category category) {
        return new ProductCategory(product, category);
    }
}
