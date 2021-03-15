package com.sellerapp.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sellerapp.order.OrderItems;
import com.sellerapp.order.Orders;
import com.sellerapp.seller.Seller;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@Table(name = "Product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "sku_id")
    private String sku_id;

    @NotNull
    @Column(name = "price")
    private float price;

    @NotNull
    @Column(name="available")
    private boolean available;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sid")
    private Seller seller;

}
