package com.sellerapp.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sellerapp.seller.SellerEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "Product")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "sku_id")
    private String sku_id;

    @Column(name="upc")
    private String upc;

    @Column(name = "ean")
    private String ean;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name="available")
    private boolean available;

    @Column(name="basic_eta")
    private int basic_eta;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sid")
    private SellerEntity sellerEntity;

}
