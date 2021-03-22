package com.sellerapp.customer;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sellerapp.order.Orders;
import com.sellerapp.product.Product;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "Customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int cid;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "name")
    private String name;

    /*@OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders;*/


}

