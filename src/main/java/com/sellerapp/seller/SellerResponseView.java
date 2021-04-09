package com.sellerapp.seller;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SellerResponseView {
    String name;
    Date date;
    String email;
    boolean available;
    String shortName;
    String phone;
    String type;
    String latLon;
}