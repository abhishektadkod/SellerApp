package com.sellerapp.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sellerapp.customer.Customer;
import com.sellerapp.product.Product;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@Table(name = "Orders")
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    private int oid;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cid")
    private Customer customer;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orders")
    @JsonManagedReference
    private List<OrderItems> orderItems = new ArrayList<OrderItems>();

}
