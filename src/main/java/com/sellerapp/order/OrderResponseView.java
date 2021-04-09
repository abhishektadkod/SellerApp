package com.sellerapp.order;

import com.sellerapp.seller.SellerEntity;
import com.sellerapp.seller.SellerResponseView;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponseView {
    private int oid;
    private CustomerEntity customer;
    private SellerResponseView seller;
    private Date date;
    private String status;
    private String businessUnit;
    private float totalPrice;
    private List<OrderItemsEntity> orderItem;
}

