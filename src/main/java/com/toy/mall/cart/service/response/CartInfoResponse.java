package com.toy.mall.cart.service.response;

import lombok.Getter;

@Getter
public class CartInfoResponse {

    private Long id;
    private String productName;
    private int price;
    private int quantity;
    private int amount;

    public CartInfoResponse(Long id, String productName, int price, int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.amount = price * quantity;
    }

    @Override
    public String toString() {
        return "CartInfoResponse{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
