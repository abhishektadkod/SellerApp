package com.sellerapp.seller;

import com.sellerapp.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerservice;

    @RequestMapping("/sellers")
    public List<SellerEntity> getSellers(){ return sellerservice.getSeller(); }

    @RequestMapping("/details/seller")
    public SellerResponseView getSellerId(HttpServletRequest request) throws AuthException{
        try {
            int sid = (Integer) request.getAttribute("sid");
            return sellerservice.getSellerById(sid);
        }
        catch(Exception e) {
            throw new AuthException("Authentication token not provided!");
        }
    }

    @PostMapping("/register/seller")
     public ResponseEntity<Map<String, String>> addSeller(@RequestBody SellerRequestView seller) throws AuthException{
        return ResponseEntity.ok(sellerservice.addSeller(seller));
    }

    @PostMapping("/login/seller")
    public ResponseEntity<Map<String, String>> loginSeller(@RequestBody SellerRequestView seller) throws AuthException {
        return ResponseEntity.ok(sellerservice.loginSeller(seller));
    }

    @PutMapping("/update/seller")
    public ResponseEntity<Map<String, String>> updateSeller(HttpServletRequest request, @RequestBody SellerRequestView seller) throws AuthException{
        try {
            int sid = (Integer) request.getAttribute("sid");
            return ResponseEntity.ok(sellerservice.updateSeller(sid,seller.isAvailable()));
        }
        catch (Exception e){
            throw new AuthException("Authentication token not provided!");
        }
    }

    @DeleteMapping("/delete/seller")
    public void deleteSeller(HttpServletRequest request) throws AuthException{
        int sid =(Integer) request.getAttribute("sid");
        sellerservice.deleteSeller(sid);
    }
}
