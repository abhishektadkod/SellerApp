package com.sellerapp.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sellerapp.seller.Seller;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private float price;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sid")
    private Seller seller;

}
