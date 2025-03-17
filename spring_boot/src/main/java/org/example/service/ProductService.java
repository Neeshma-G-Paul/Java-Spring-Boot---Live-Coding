package org.example.service;

import org.example.request.ProductRequest;
import org.example.response.ProductResponse;

public interface ProductService {

    ProductResponse priceCalculation(ProductRequest productRequest);
}
