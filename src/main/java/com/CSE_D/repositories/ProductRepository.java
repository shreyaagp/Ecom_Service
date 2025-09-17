package com.CSE_D.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CSE_D.model.Products;

@Repository
public interface ProductRepository extends MongoRepository<Products, String> {
    // No additional code needed; built-in CRUD works
}
