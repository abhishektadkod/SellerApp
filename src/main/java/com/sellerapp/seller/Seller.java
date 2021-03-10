package com.sellerapp.seller;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sellerapp.product.Product;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "Seller")
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private int sid;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference
    private List<Product> products;

}

