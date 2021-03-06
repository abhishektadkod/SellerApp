package com.sellerapp.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItemsEntity {

    private int itemId;
    private String productName;
    private String skuId;
    private  String upc;
    private String description;
    private  String image;
    private int basic_etc;
    private  float price;
    private int quantity;

}
