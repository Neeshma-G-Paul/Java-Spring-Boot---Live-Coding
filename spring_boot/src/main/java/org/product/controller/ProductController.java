package org.product.controller;


import org.product.request.ProductRequest;
import org.product.response.ProductResponse;
import org.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/price-calculation")
    public ProductResponse priceCalculation(@RequestBody ProductRequest productRequest){
    return productService.priceCalculation(productRequest);
    }
}
