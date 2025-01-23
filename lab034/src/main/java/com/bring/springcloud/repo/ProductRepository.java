package com.bring.springcloud.repo;

import com.bring.springcloud.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {


    Optional<Product> findByName(String iphone);
}
