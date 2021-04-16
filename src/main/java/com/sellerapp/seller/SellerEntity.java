package com.sellerapp.seller;

import com.sun.istack.NotNull;
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

@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Seller")
@Entity
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerId")
    private int sellerId;
    
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "datetime")
    @CreationTimestamp
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "phone")
    private String phone;

    @Column(name="available")
    private boolean available;

    @Column(name="type")
    private String type;

    @Type(type = "jsonb")
    @Column(name="location",columnDefinition = "jsonb")
    private SellerLocationEntity location;

}