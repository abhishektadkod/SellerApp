package com.sellerapp.product;

import com.sellerapp.seller.SellerEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductDTO {

    public ProductEntity ConvertToProductEntity(ProductEntity productEntity , int sid){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerId(sid);
        productEntity.setSellerEntity(sellerEntity);
        return productEntity;
    }
}
