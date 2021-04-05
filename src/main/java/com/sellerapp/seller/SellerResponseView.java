package com.sellerapp.seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SellerResponseView {
    String name;
    Date date;
    String email;
    boolean available;
}
