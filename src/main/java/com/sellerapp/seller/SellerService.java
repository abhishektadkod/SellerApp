package com.sellerapp.seller;

import com.sellerapp.AuthException;
import com.sellerapp.DatabaseException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerDTO sellerDTO;

    @Autowired
    private SellerJwt sellerJwt;


    public List<SellerEntity> getSeller() {
        List<SellerEntity> sellerEntities = new ArrayList<>();
        sellerRepository.findAll().forEach(sellerEntities::add);
        return sellerEntities;
    }

    public SellerResponseView getSellerById(int id){
        Optional<SellerEntity> seller = sellerRepository.findById(id);
        SellerResponseView response = sellerDTO.ConvertToResponseView(seller.get());
        return response;
    }

    public Map<String,String> addSeller(SellerRequestView sellerRequestView) throws DatabaseException {
        String password = sellerRequestView.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        sellerRequestView.setPassword(hashedPassword);
        SellerEntity sellerEntity = sellerDTO.ConvertToSellerEntity(sellerRequestView);
        Long count = sellerRepository.countByEmail(sellerRequestView.getEmail());
        if (count > 0)
            throw new DatabaseException("Email already in use");
        sellerRepository.save(sellerEntity);
        Map<String, String> response = new HashMap<>();
        response.put("token", sellerJwt.generateJWT(sellerEntity));
        response.put("response", "Login Successful!");
        return response;
    }

    public Map<String, String> loginSeller(SellerRequestView sellerRequestView) throws DatabaseException{
        String password = sellerRequestView.getPassword();
        String email  = sellerRequestView.getEmail();


        try {
            if(!BCrypt.checkpw(password, sellerRepository.findByEmail(email).getPassword()))
                throw new AuthException("Invalid email/password");

            Map<String, String> response = new HashMap<>();
            response.put("token", sellerJwt.generateJWT(sellerRepository.findByEmail(email)));
            response.put("response", "Login Successful!");
            return response;
        }catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid email/password");
        }
        catch (java.lang.NullPointerException e){
            throw new AuthException("Invalid email/password");
        }
    }

    public Map<String, String> updateSeller(int sid, boolean available) {

        Map<String, String> map = new HashMap<>();
            Optional<SellerEntity> seller = sellerRepository.findById(sid);
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
