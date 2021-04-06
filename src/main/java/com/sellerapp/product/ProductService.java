package com.sellerapp.product;

import com.sellerapp.DatabaseException;
import com.sellerapp.seller.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return productRepository.findBySellerEntitySellerId(id);
    }

    public Map<String, String> addProduct(Product product, int sid) {
        Map<String, String> map = new HashMap<>();
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerId(sid);
        product.setSellerEntity(sellerEntity);
        System.out.println(product.getSellerEntity().getName());
        productRepository.save(product);
        map.put("response", "Product Added Successfully!");
        return map;
    }

    public Map<String, String> updateProduct(Product product, int sid) throws DatabaseException{
        Optional<Product> products = productRepository.findById(product.getPid());
        Map<String, String> map = new HashMap<>();
        if(products.isPresent()){
            if(products.get().getSellerEntity().getSellerId()==sid){
                products.get().setAvailable(product.isAvailable());
                productRepository.save(products.get());
                map.put("response", "Update Successful!");
            }
            else {
                throw new DatabaseException("This product Not Registered for the Seller");
            }
        }
        else{
            throw new DatabaseException("Product not present in the database!");
        }
        return map;
    }

    public void deleteProduct(int id) { productRepository.deleteById(id); }

}
