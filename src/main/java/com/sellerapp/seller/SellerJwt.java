package com.sellerapp.seller;

import com.sellerapp.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SellerJwt {
    public String generateJWT(Seller seller) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("email", seller.getEmail())
                .claim("name", seller.getName())
                .claim("available", seller.isAvailable())
                .compact();
        return token;
    }
}
