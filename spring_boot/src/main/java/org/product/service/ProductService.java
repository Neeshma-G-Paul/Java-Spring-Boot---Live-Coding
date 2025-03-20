package org.product.service;

import org.product.request.ProductRequest;
import org.product.response.ProductResponse;

public interface ProductService {

    ProductResponse priceCalculation(ProductRequest productRequest);
}
