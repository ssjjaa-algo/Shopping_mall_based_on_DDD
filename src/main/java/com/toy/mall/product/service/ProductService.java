package com.toy.mall.product.service;

import com.toy.mall.category.domain.Category;
import com.toy.mall.category.service.cqrs.CategoryQueryPort;
import com.toy.mall.product.domain.Product;
import com.toy.mall.product.domain.ProductCategory;
import com.toy.mall.product.service.cqrs.ProductCommandPort;
import com.toy.mall.product.service.cqrs.ProductQueryPort;
import com.toy.mall.product.service.request.ProductServiceCreateRequest;
import com.toy.mall.product.service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<ProductResponse> getProducts(String categoryName, int pageNum) {

        Category category = categoryQueryPort.findByName(categoryName);
        List<Long> categoriesId = category.extractLowestCategoryIds();

        return productQueryPort.findDistinctByCategories_Category_IdIn(categoriesId,
                PageRequest.of(pageNum, 15));

    }
}
