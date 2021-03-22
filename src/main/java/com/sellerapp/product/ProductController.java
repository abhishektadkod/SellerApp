package com.sellerapp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/product")
    public List<Product> getProducts(){
        return productservice.getProduct();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductId(@PathVariable int id){
        return productservice.getProductById(id);
    }

    @GetMapping("/product/seller/{id}")
    public List<Product> getSellerProducts(@PathVariable int id){
        return productservice.getSellerProducts(id);
    }

    @PostMapping("/product")
     public ResponseEntity<String> addProduct(@Validated @RequestBody Product product){
        productservice.addProduct(product);
        return ResponseEntity.ok("Product Added Successfully!");
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product){ productservice.updateProduct(product);}

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){ productservice.deleteProduct(id);}
}
