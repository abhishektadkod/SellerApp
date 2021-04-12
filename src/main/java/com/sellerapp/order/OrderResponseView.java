package com.sellerapp.order;

import com.sellerapp.seller.SellerResponseView;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
class OrderResponseView {
    private int orderId;
    private CustomerEntity customer;
    private SellerResponseView seller;
    private Date orderPlacedDate;
    private String status;
    private String businessUnit;
    private float totalPrice;
    float orderFulfillmentTime;
    float orderPreparationTime;
    private List<OrderItemsEntity> orderItems;
}

@Data
@NoArgsConstructor
class OrderSellerResponseView {
    private int orderId;
    private CustomerEntity customer;
    private Date orderPlacedDate;
    private String status;
    private String businessUnit;
    private float totalPrice;
    float orderFulfillmentTime;
    float orderPreparationTime;
    private List<OrderItemsEntity> orderItems;
}
