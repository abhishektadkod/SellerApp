package com.sellerapp.product;

import com.sellerapp.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDTO productDTO;

    public List<ProductEntity> getProduct() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productRepository.findAll().forEach(productEntities::add);
        return productEntities;
    }

    public Optional<ProductEntity> getProductById(int id){
        return productRepository.findById(id);
    }

    public List<ProductEntity> getSellerProducts(int id) {
        return productRepository.findBySellerEntitySellerId(id);
    }

    public Map<String, String> addProduct(ProductEntity productEntity , int sid) {
        Map<String, String> map = new HashMap<>();
        productRepository.save(productDTO.ConvertToProductEntity(productEntity ,sid));
        map.put("response", "Product Added Successfully!");
        return map;
    }

    public Map<String, String> updateProduct(ProductEntity productEntity , int sid) throws DatabaseException{
        Optional<ProductEntity> products = productRepository.findById(productEntity.getPid());
        Map<String, String> map = new HashMap<>();
        if(products.isPresent()){
            if(products.get().getSellerEntity().getSellerId()==sid){
                products.get().setAvailable(productEntity.isAvailable());
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
