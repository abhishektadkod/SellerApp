package com.sellerapp.product;

import com.sellerapp.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/product")
    public List<ProductEntity> getProducts(){
        return productservice.getProduct();
    }

    @GetMapping("/product/{id}")
    public Optional<ProductEntity> getProductId(@PathVariable int id){
        return productservice.getProductById(id);
    }

    @GetMapping("/product/seller")
    public List<ProductEntity> getSellerProducts(HttpServletRequest request) throws AuthException{
        try {
            int id = (Integer) request.getAttribute("sid");
            return productservice.getSellerProducts(id);
        }
        catch (Exception e){
            throw new AuthException("Authorization token not provided!");
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Map<String,String>> addProduct(@RequestBody ProductEntity productEntity , HttpServletRequest request) throws AuthException {
        try {
            int sid = (Integer) request.getAttribute("sid");
            return ResponseEntity.ok(productservice.addProduct(productEntity , sid));
        } catch (Exception e) {
            throw new AuthException("Authentication token not provided!");
        }
    }

    @PutMapping("/product")
    public Map<String, String> updateProduct(@RequestBody ProductEntity productEntity , HttpServletRequest request) throws AuthException {
        try {
            int sid = (Integer) request.getAttribute("sid");
            return productservice.updateProduct(productEntity , sid);
        } catch (Exception e) {
            throw new AuthException("Authorization token not provided!");
        }
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){ productservice.deleteProduct(id);}
}
