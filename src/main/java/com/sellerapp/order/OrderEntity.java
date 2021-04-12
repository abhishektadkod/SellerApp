package com.sellerapp.order;

import com.sellerapp.seller.SellerEntity;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    @Type(type = "jsonb")
    @Column(name="customer",columnDefinition = "jsonb")
    private CustomerEntity customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sid")
    private SellerEntity sellerEntity;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date orderDate;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "businessUnit")
    private String businessUnit;

    @Type(type = "jsonb")
    @Column(name="order_items",columnDefinition = "jsonb")
    private List<OrderItemsEntity> orderItemEntities;

    @NotNull
    @Column(name="total")
    private float totalPrice;

    @NotNull
    @Column(name="orderPreparationTime")
    private float orderPreparationTime;

    @NotNull
    @Column(name="orderFulfillmentTime")
    private float orderFulfillmentTime;

}
