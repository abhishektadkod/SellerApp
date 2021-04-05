package com.sellerapp.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sellerapp.customer.Customer;
import com.sellerapp.product.Product;
import com.sellerapp.seller.Seller;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;


@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})


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
    @JoinColumn(name = "cid")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sid")
    private Seller seller;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "source")
    private String source;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<OrderItems> orderItems;

    @NotNull
    @Column(name="total")
    private float totalPrice;

}
