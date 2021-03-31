package com.sellerapp.order;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sellerapp.order.Orders;
import com.sellerapp.product.Product;
import com.sellerapp.seller.Seller;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Table(name = "OrderItems")
@Entity
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int item_id;

    @NotNull
    @OneToOne
    @JoinColumn(name="pid")
    private Product products;

    @Column(name="quantity")
    private int quantity;


    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name="oid")
    private Orders orders;


}
