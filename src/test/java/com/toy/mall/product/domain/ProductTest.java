package com.toy.mall.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("입력된 수량보다 재고가 적은 상품에 대하여 예외 발생")
    public void checkStockLogic() {
        //given
        Product product = Product.create("test", 3000, 15);
        //when
        Assertions.assertThrows(IllegalStateException.class, () -> product.checkProductStock(17));
        Assertions.assertThrows(IllegalStateException.class, () -> product.checkProductStock(0));
        //then
    }
}