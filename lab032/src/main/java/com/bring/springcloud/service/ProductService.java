package com.bring.springcloud.service;

import com.bring.springcloud.dto.ProductRequest;
import com.bring.springcloud.dto.ProductResponse;
import com.bring.springcloud.models.Product;
import com.bring.springcloud.repo.ProductRepository;
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

        return ProductResponse.builder()
                .id((product.getId()))
                .name(product.getName())
                .description((product.getDescription()))
                .price((product.getPrice()))
                .build();


    }
}
