package com.bring.products.service;

import com.bring.products.dto.ProductRequest;
import com.bring.products.dto.ProductResponse;
import com.bring.products.models.Product;
import com.bring.products.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;



    public void createProduct(ProductRequest productRequest){


        Product product= Product.builder()
                .name(productRequest.getName())
                .description((productRequest.getDescription()))
                .price((productRequest.getPrice()))
                .build();

        productRepository.save(product);

        log.info ("Product  {} is saved. ", product.getName());

    }

    public List<ProductResponse> getProducts() {

       List<Product> products= productRepository.findAll();

      return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {

        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        System.out.println("Proucts Listing from mongo : "+response);

        return response;



    }
}
