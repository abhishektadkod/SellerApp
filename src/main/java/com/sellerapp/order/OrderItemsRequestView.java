package com.sellerapp.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItemsRequestView {

    private int itemId;
    private int productsId;
    private int quantity;

}
