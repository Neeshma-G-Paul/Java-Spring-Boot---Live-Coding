package org.product.request;

import lombok.Data;

@Data
public class ProductRequest {


    private String productId;
    private Integer quantity;
    private String promoCode;
    private String userType;
}
