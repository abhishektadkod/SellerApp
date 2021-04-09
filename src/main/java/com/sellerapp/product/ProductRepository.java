package com.sellerapp.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findBySellerEntitySellerId(int id);
}
