package com.sellerapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestView{

    CustomerEntity customer;
    int sellerId;
    String status;
    String businessUnit;
    List<OrderItemsRequestView> orderItems;
}
