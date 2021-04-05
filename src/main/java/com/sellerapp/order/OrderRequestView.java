package com.sellerapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestView{

    int cid;
    int sid;
    String status;
    String source;
    List<OrderItemsRequestView> orderItems;
}
