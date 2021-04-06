package com.sellerapp.order;

import com.sellerapp.customer.Customer;
import com.sellerapp.seller.SellerEntity;
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
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sid")
    private SellerEntity sellerEntity;

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
    @Column(name="order_items",columnDefinition = "jsonb")
    private List<OrderItemsEntity> orderItemEntities;

    @NotNull
    @Column(name="total")
    private float totalPrice;

}
