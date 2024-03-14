package com.toy.mall.product.service;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.service.cqrs.CategoryQueryPort;
import com.toy.mall.product.domain.Product;
import com.toy.mall.product.domain.ProductCategory;
import com.toy.mall.product.service.cqrs.ProductCommandPort;
import com.toy.mall.product.service.cqrs.ProductQueryPort;
import com.toy.mall.product.service.request.ProductServiceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductCommandPort productCommandPort;
    private final ProductQueryPort productQueryPort;
    private final CategoryQueryPort categoryQueryPort;

    public void create(ProductServiceCreateRequest serviceRequest) {

        Category category = categoryQueryPort.findByName(serviceRequest.getCategory());

        if (!category.getChild().isEmpty()) {
            throw new IllegalStateException("최하위 카테고리에만 등록할 수 있음");
        }

        if (productQueryPort.existsByName(serviceRequest.getName())) {
            throw new IllegalStateException("이미 동일한 상품 이름이 존재");
        }

        Product product = Product.create(serviceRequest.getName(), serviceRequest.getPrice(),
                 serviceRequest.getStockQuantity());
        ProductCategory productCategory = ProductCategory.create(product, category);
        product.addCategory(productCategory);

        productCommandPort.save(product);
    }
}
