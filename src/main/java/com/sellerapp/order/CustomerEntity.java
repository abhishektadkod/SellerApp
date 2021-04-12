package com.sellerapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    private String name;
    private String email;
    private long phone;
    private String address;
    private SphericalCoordinatesView location;

}

