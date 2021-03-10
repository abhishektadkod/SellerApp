package com.sellerapp.product;

import com.sellerapp.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProduct() {
        List<Product> Products = new ArrayList<>();
        productRepository.findAll().forEach(Products::add);
        return Products;
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

    public List<Product> getSellerProducts(int id) {
        return productRepository.findBySellerSid(id);
    }

    public void addProduct(Product product) {
        System.out.println(product.getSeller().getName());
        productRepository.save(product);
    }

    public void updateProduct(Product product) { productRepository.save(product); }

    public void deleteProduct(int id) { productRepository.deleteById(id); }

}
