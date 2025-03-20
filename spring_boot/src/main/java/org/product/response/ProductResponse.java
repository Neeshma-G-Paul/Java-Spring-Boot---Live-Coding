package org.product.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String productId;
    private Double originalPrice;
    private Double finalPrice;
    private List<AppliedDiscount> appliedDiscounts;
    private Double totalSavings;
}
