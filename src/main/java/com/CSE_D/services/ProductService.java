package com.CSE_D.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSE_D.exceptions.IdNotPresentException;
import com.CSE_D.model.Products;
import com.CSE_D.repositories.ProductRepository;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    // Get all products
    public List<Products> getAllProducts() {
        logger.info("API Call: GET /products - Retrieving all products");
        return repository.findAll();
    }

    // Get product by ID
    public Products getByProductId(String id) throws IdNotPresentException {
        logger.debug("API Call: GET /products/{} - Retrieving product", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("API Error: Product not found with id: {}", id);
                    return new IdNotPresentException("Product not found with id: " + id);
                });
    }

    // Add product
    public Products addProduct(Products product) {
        logger.info("API Call: POST /products - Adding new product: {}", product.getName());
        return repository.save(product);
    }

    // Update product
    public Products updateProduct(String id, Products product) throws IdNotPresentException {
        Products existingProduct = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("API Error: Product not found with id: {}", id);
                    return new IdNotPresentException("Product not found with id: " + id);
                });

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setTags(product.getTags());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());

        logger.info("API Call: PUT /products/{} - Updating product", id);
        return repository.save(existingProduct);
    }

    // Delete product
    public void deleteProduct(String id) throws IdNotPresentException {
        Products existingProduct = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("API Error: Product not found with id: {}", id);
                    return new IdNotPresentException("Product not found with id: " + id);
                });

        repository.delete(existingProduct);
        logger.info("API Call: DELETE /products/{} - Deleted product successfully", id);
    }
}
