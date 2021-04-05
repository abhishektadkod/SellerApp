package com.sellerapp.seller;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerDTO {

    public SellerResponseView ConvertToResponseView(Optional<SellerEntity> seller){
        SellerResponseView response = new SellerResponseView();
        response.setName(seller.get().getName());
        response.setAvailable(seller.get().isAvailable());
        response.setEmail(seller.get().getEmail());
        response.setDate(seller.get().getDate());
        return response;
    }

    public SellerEntity ConvertToSellerEntity(SellerRequestView sellerRequestView){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setName(sellerRequestView.getName());
        sellerEntity.setEmail(sellerRequestView.getEmail());
        sellerEntity.setAvailable(sellerRequestView.isAvailable());
        sellerEntity.setPassword(sellerRequestView.getPassword());
        return sellerEntity;
    }

}
