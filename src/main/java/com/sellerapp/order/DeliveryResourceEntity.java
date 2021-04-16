package com.sellerapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResourceEntity {
    private String driverName;
    private long phone;
    private String image;
    private String VehicleNumber;
    private String licenseNumber;
    private String threePLName;
}
