package com.CSE_D.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CSE_D.model.Products;
import com.CSE_D.services.ProductService;

@CrossOrigin(origins = "*") // Allow frontend requests
@RestController
@RequestMapping("/products") // Base path
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;
    @GetMapping("/hello")
    public String sayhello(){
        return "hello" ;
    }
    // Get all products
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        logger.info("API Call: GET /products - Request received");
        List<Products> productsList = service.getAllProducts();
        logger.info("API Response: GET /products - {} products found", productsList.size());
        return ResponseEntity.ok(productsList);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable String id) {
        logger.info("API Call: GET /products/{} - Request received", id);
        Products product = service.getByProductId(id);
        logger.info("API Response: GET /products/{} - Product retrieved successfully", id);
        return ResponseEntity.ok(product);
    }

    // Add new product
    @PostMapping
    public ResponseEntity<Products> addProduct(@RequestBody Products product) {
        logger.info("API Call: POST /products - Request to add product: {}", product.getName());
        Products savedProduct = service.addProduct(product);
        logger.info("API Response: POST /products - Product {} created with ID {}", 
                    savedProduct.getName(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // Update product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id,
                                                  @RequestBody Products product) {
        logger.info("API Call: PUT /products/{} - Request to update product", id);
        Products updatedProduct = service.updateProduct(id, product);
        logger.info("API Response: PUT /products/{} - Product updated successfully", id);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        logger.info("API Call: DELETE /products/{} - Request received", id);
        service.deleteProduct(id);
        logger.info("API Response: DELETE /products/{} - Product deleted successfully", id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    public String sayHello() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
