package com.bring.springcloud.controller;


import com.bring.springcloud.dto.ProductRequest;
import com.bring.springcloud.dto.ProductResponse;
import com.bring.springcloud.models.Product;
import com.bring.springcloud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;



    //curl -i -X POST http://localhost:8080/api/product -H "Content-Type: application/json" -d '{"name": "Jacket", "description": "FilaJacket", "price": 10.99}'


    //curl -i -X POST http://localhost:8080/api/product -H "Content-Type: application/json" -d '{"name": "Phone", "description": "IPhone", "price": 110.99}'

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){

        productService.createProduct(productRequest);

    }


    //curl -i -X GET http://localhost:8080/api/product
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts(){

        return productService.getProducts();

    }
}
