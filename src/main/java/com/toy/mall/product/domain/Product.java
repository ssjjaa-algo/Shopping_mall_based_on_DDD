package com.toy.mall.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private SellingStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCategory> categories = new ArrayList<>();

    /**
     * protected로 생성자를 보호하긴 하나, 상속을 이용 못하게 된다.
     * 이펙티브 자바 3판에 따르면 이는 단점, 장점이 될 수 있다고도 하는데
     * 상속이 아닌 컴포지션을 유도하여 상쇄한다고 한다.
     * 요구사항에 따라 달라지는 부분으로 염두에 둔다.
     */
    protected Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categories = new ArrayList<>();
        this.status = SellingStatus.SELLING;
    }

    public static Product create(String name, int price, int stockQuantity) {

        return new Product(name, price, stockQuantity);
    }

    public void addCategory(ProductCategory productCategory) {
        this.categories.add(productCategory);
    }

    public void canAddToCart(int count) {

        if (this.status != SellingStatus.SELLING) {
            throw new IllegalStateException("해당 상품은 판매중이지 않은 상품");
        }

        checkProductStock(count);
    }

    public void checkProductStock(int count) {

        if (this.stock < count || this.stock <= 0) {
            throw new IllegalStateException("해당 상품의 재고가 부족합니다.");
        }

        if (count <= 0) {
            throw new IllegalStateException("0개 이상의 수량을 선택해야 합니다.");
        }
    }

    public void deductStock(int count) {
        checkProductStock(count);
        this.stock -= count;
    }
}
