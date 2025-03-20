package org.product.controller;

import org.product.request.ProductRequest;
import org.product.response.ProductResponse;
import org.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductService productService;

    @Test
    void priceCalculation() {
        ProductRequest productRequest = getProductDetails();
        ProductResponse productResponse = productService.priceCalculation(productRequest);
        Assertions.assertNotNull(productResponse);
        assert productResponse.getTotalSavings().equals(37.55874375);
    }

    private ProductRequest getProductDetails() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("ABC123");
        productRequest.setQuantity(5);
        productRequest.setPromoCode("SPRING25");
        productRequest.setUserType("PREMIUM");
        return productRequest;
    }
}
