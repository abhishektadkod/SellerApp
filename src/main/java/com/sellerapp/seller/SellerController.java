package com.sellerapp.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerservice;

    @RequestMapping("/seller")
    public List<Seller> getSellers(){ return sellerservice.getSeller(); }

    @RequestMapping("/seller/{id}")
    public Optional<Seller> getSellerId(@PathVariable int id){
        return sellerservice.getSellerById(id);
    }

    @PostMapping("/seller")
     public void addSeller(@RequestBody Seller seller){
        sellerservice.addSeller(seller);
    }

    @PutMapping("/seller")
    public void updateSeller(@RequestBody Seller seller){ sellerservice.updateSeller(seller);}

    @DeleteMapping("/seller/{id}")
    public void deleteSeller(@PathVariable int id){ sellerservice.deleteSeller(id);}
}
