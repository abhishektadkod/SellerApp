package com.sellerapp.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponseView {

    private int oid;
    private String customer;
    private String seller;
    private Date date;
    private String status;
    private String source;
    private float totalPrice;
    private List<OrderItemsEntity> orderItem;

}
