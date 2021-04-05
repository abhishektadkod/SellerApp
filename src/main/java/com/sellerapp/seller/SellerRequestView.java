package com.sellerapp.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestView {
    String name;
    String email;
    String password;
    boolean available;
}
