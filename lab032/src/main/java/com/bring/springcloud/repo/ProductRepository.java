package com.bring.springcloud.repo;

import com.bring.springcloud.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {


}
