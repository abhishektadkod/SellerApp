package com.sellerapp.seller;

import com.sellerapp.AuthException;
import com.sellerapp.Constants;
import com.sellerapp.DatabaseException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    private Map<String, String> generateJWTToken(Seller seller) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("email", seller.getEmail())
                .claim("Name", seller.getName())
                .claim("Available", seller.isAvailable())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("response", "Login Successful!");
        return map;
    }

    public List<Seller> getSeller() {
        List<Seller> sellers = new ArrayList<>();
        sellerRepository.findAll().forEach(sellers::add);
        return sellers;
    }

    public Optional<Seller> getSellerById(int id){
        return sellerRepository.findById(id);
    }

    public Map<String,String> addSeller(Seller seller) throws DatabaseException {
        String password = seller.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        seller.setPassword(hashedPassword);
        Long count = sellerRepository.countByEmail(seller.getEmail());
        if (count > 0)
            throw new DatabaseException("Email already in use");
        sellerRepository.save(seller);
        return generateJWTToken(seller);
    }

    public Map<String, String> loginSeller(Seller seller) throws DatabaseException{
        String password = seller.getPassword();
        String email  = seller.getEmail();

        try {
            if(!BCrypt.checkpw(password, sellerRepository.findByEmail(email).getPassword()))
                throw new AuthException("Invalid email/password");

            return generateJWTToken(sellerRepository.findByEmail(email));
        }catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid email/password");
        }
        catch (java.lang.NullPointerException e){
            throw new AuthException("Invalid email/password");
        }
    }

    public Map<String, String> updateSeller(int sid, boolean available) {

        Map<String, String> map = new HashMap<>();
            Optional<Seller> seller = sellerRepository.findById(sid);
            if(seller.isPresent()){
                seller.get().setAvailable(available);
                sellerRepository.save(seller.get());
                map.put("response", "Update Successful!");
            }
            else{
                throw new DatabaseException("Seller not present! Login again");
            }
    return map;

    }

    public void deleteSeller(int id){
        sellerRepository.deleteById(id);
    }

}
