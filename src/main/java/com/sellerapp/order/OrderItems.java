package com.sellerapp.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItems {

    private int itemId;
    private String productName;
    private String skuId;
    private int orderCompletionTime;
    private  float price;
    private int quantity;

}
