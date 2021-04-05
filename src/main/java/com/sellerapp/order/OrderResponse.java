package com.sellerapp.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {

    private int oid;
    private String customer;
    private String seller;
    private Date date;
    private String status;
    private String source;
    private float totalPrice;
    private List<OrderItems> orderItems = new ArrayList<OrderItems>();

    public List<OrderResponse> getOrderResponse(List<Orders> orders){
        List<OrderResponse> Orders = new ArrayList<>();
        for(Orders o:orders){
            OrderResponse temp = new OrderResponse();
            temp.setOid(o.getOid());
            temp.setCustomer(o.getCustomer().getName());
            temp.setSeller(o.getSellerEntity().getName());
            temp.setDate(o.getDate());
            temp.setSource(o.getSource());
            temp.setStatus(o.getStatus());
            temp.setTotalPrice(o.getTotalPrice());
            temp.setOrderItems(o.getOrderItems());
            Orders.add(temp);
        }
        return Orders;
    }
}
