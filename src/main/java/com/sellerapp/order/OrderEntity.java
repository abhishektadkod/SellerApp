package com.sellerapp.order;

import com.sellerapp.seller.SellerEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Type(type = "jsonb")
    @Column(name="deliveryResource",columnDefinition = "jsonb")
    private DeliveryResourceEntity deliveryResource;

    @ManyToOne
    @JoinColumn(name = "sid")
    private SellerEntity sellerEntity;
    
    @Column(name = "datetime")
    @CreationTimestamp
    private Date orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "businessUnit")
    private String businessUnit;

    @Type(type = "jsonb")
    @Column(name="order_items",columnDefinition = "jsonb")
    private List<OrderItemsEntity> orderItemEntities;

    @Column(name="total")
    private float totalPrice;

    @Column(name="orderPreparationTime")
    private float orderPreparationTime;
    
    @Column(name="orderFulfillmentTime")
    private float orderFulfillmentTime;

}
