package com.sellerapp.seller;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "Seller")
@Entity
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerId")
    private int sellerId;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "shortName")
    private String shortName;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name="available")
    private boolean available;

    @NotNull
    @Column(name="type")
    private String type;

    @NotNull
    @Column(name="latLon")
    private String latLon;


}