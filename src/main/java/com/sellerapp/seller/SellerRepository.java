package com.sellerapp.seller;

import org.springframework.data.repository.CrudRepository;


public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
    SellerEntity findByEmail(String email);
    Long countByEmail(String email);
}
