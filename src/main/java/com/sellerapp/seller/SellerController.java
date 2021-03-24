package com.sellerapp.seller;

import com.sellerapp.AuthException;
import com.sellerapp.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SellerController {

    Logger logger = Logger.getLogger(SellerController.class.getName());

    @Autowired
    private SellerService sellerservice;

    @RequestMapping("/sellers")
    public List<Seller> getSellers(){ return sellerservice.getSeller(); }

    @RequestMapping("/details/seller")
    public Optional<Seller> getSellerId(HttpServletRequest request) throws AuthException{

        logger.log(Level.INFO, "logging: {0} ", request.getRequestURL().toString());

        try {
            int sid = (Integer) request.getAttribute("sid");
            return sellerservice.getSellerById(sid);
        }
        catch(Exception e) {
            throw new AuthException(e.getMessage().toString());
        }
    }

    @PostMapping("/register/seller")
     public ResponseEntity<Map<String, String>> addSeller(@RequestBody Seller seller) throws AuthException{
        return ResponseEntity.ok(sellerservice.addSeller(seller));
    }

    @PostMapping("/login/seller")
    public ResponseEntity<Map<String, String>> loginSeller(@RequestBody Seller seller) throws AuthException {
        return ResponseEntity.ok(sellerservice.loginSeller(seller));
    }

    @PutMapping("/update/seller")
    public Map<String, String> updateSeller(HttpServletRequest request, @RequestBody Seller seller) throws AuthException{
        int sid =(Integer) request.getAttribute("sid");
        return sellerservice.updateSeller(sid,seller.isAvailable());
    }

    @DeleteMapping("/delete/seller")
    public void deleteSeller(HttpServletRequest request) throws AuthException{
        int sid =(Integer) request.getAttribute("sid");
        sellerservice.deleteSeller(sid);
    }
}
