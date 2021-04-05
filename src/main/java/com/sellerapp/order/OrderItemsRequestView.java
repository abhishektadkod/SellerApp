package com.sellerapp.order;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sellerapp.order.Orders;
import com.sellerapp.product.Product;
import com.sellerapp.seller.Seller;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItemsRequestView {

    private int itemId;
    private int productsId;
    private int quantity;

}
