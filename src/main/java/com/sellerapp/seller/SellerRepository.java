package com.sellerapp.seller;

import org.springframework.data.repository.CrudRepository;


public interface SellerRepository extends CrudRepository<Seller, Integer> {
    Seller findByEmail(String email);
    Long countByEmail(String email);
}
