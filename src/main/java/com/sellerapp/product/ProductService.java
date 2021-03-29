package com.sellerapp.product;

import com.sellerapp.AuthException;
import com.sellerapp.DatabaseException;
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

    public void addProduct(Product product,int sid) {
        Seller seller = new Seller();
        seller.setSid(sid);
        product.setSeller(seller);
        System.out.println(product.getSeller().getName());
        productRepository.save(product);
    }

    public void updateProduct(Product product, int sid) throws DatabaseException{
        Optional<Product> products = productRepository.findById(product.getPid());
        if(products.isPresent()){
            if(products.get().getSeller().getSid()==sid){
                products.get().setAvailable(product.isAvailable());
                productRepository.save(products.get());
            }
            else {
                throw new DatabaseException("This product Not Registered for the Seller");
            }
        }
        else{
            throw new DatabaseException("Product not present in the database!");
        }
    }

    public void deleteProduct(int id) { productRepository.deleteById(id); }

}
