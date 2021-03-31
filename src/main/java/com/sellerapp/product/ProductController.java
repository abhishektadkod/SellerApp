package com.sellerapp.product;

import com.sellerapp.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/product/seller")
    public List<Product> getSellerProducts(HttpServletRequest request) throws AuthException{
        try {
            int id = (Integer) request.getAttribute("sid");
            return productservice.getSellerProducts(id);
        }
        catch (Exception e){
            throw new AuthException("Authorization token not provided!");
        }
    }

    @PostMapping("/product")
     public ResponseEntity<String> addProduct(@RequestBody Product product,HttpServletRequest request){
        int sid =(Integer) request.getAttribute("sid");
        productservice.addProduct(product,sid);
        return ResponseEntity.ok("Product Added Successfully!");
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product,HttpServletRequest request) throws AuthException {
        try {
            int sid = (Integer) request.getAttribute("sid");
            productservice.updateProduct(product, sid);
        } catch (Exception e) {
            throw new AuthException("Authorization token not provided!");
        }
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){ productservice.deleteProduct(id);}
}
