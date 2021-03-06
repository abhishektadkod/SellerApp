package com.sellerapp.seller;

import org.springframework.stereotype.Service;

@Service
public class SellerDTO {

    public SellerResponseView ConvertToResponseView(SellerEntity seller){
        SellerResponseView response = new SellerResponseView();
        response.setName(seller.getName());
        response.setAvailable(seller.isAvailable());
        response.setEmail(seller.getEmail());
        response.setDate(seller.getDate());
        response.setShortName(seller.getShortName());
        response.setPhone(seller.getPhone());
        response.setLocation(seller.getLocation());
        response.setType(seller.getType());
        return response;
    }

    public SellerEntity ConvertToSellerEntity(SellerRequestView sellerRequestView){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setName(sellerRequestView.getName());
        sellerEntity.setEmail(sellerRequestView.getEmail());
        sellerEntity.setAvailable(sellerRequestView.isAvailable());
        sellerEntity.setPassword(sellerRequestView.getPassword());
        sellerEntity.setShortName(sellerRequestView.getShortName());
        sellerEntity.setPhone(sellerRequestView.getPhone());
        sellerEntity.setLocation(sellerRequestView.getLocation());
        sellerEntity.setType(sellerRequestView.getType());
        return sellerEntity;
    }

}
