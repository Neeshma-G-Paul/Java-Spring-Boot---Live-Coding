package org.example.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductRequest {


    private String productId;
    private Integer quantity;
    private String promoCode;
    private String userType;
}
