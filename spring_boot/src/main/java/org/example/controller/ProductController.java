package org.example.controller;


import org.example.request.ProductRequest;
import org.example.response.ProductResponse;
import org.example.service.ProductService;
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
