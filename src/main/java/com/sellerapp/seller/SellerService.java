package com.sellerapp.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getSeller() {
        List<Seller> sellers = new ArrayList<>();
        sellerRepository.findAll().forEach(sellers::add);
        return sellers;
    }

    public Optional<Seller> getSellerById(int id){
        return sellerRepository.findById(id);
    }

    public void addSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public void updateSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public void deleteSeller(int id){
        sellerRepository.deleteById(id);
    }

    public String loginSeller(Seller seller) {
        return "HI";
    }
}
