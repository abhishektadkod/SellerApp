package com.sellerapp.order;

import com.sellerapp.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<OrderResponseView> getOrdersS(){
        return orderservice.getOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderResponseView getOrdersId(@PathVariable int id){
        return orderservice.getOrdersById(id);
    }

    @GetMapping("/orders/seller")
    public Set<OrderSellerResponseView> getOrdersBySeller(HttpServletRequest request) throws AuthException{
        try {
            int sid = (Integer) request.getAttribute("sid");
            return orderservice.getOrdersBySeller(sid);
        }
     catch(Exception e) {
            throw new AuthException("Authentication token not provided!");
        }}


    @PostMapping(value="/orders")
    public ResponseEntity<Map<String,String>> addOrders (@Validated @RequestBody OrderRequestView Orders){
        orderservice.addOrders(Orders);
        Map<String,String> map = new HashMap<>();
        map.put("response","Order Added Successfully!");
        return ResponseEntity.ok(map);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Map<String,String>> updateOrdersById(HttpServletRequest request, @RequestBody OrderEntity OrderEntity, @PathVariable int id) throws AuthException{
        try {
            orderservice.updateOrdersById(OrderEntity, id);
            Map<String, String> map = new HashMap<>();
            map.put("response", "Order Updated Successfully!");
            return ResponseEntity.ok(map);
        }
         catch(Exception e) {
            throw new AuthException("Authentication token not provided!");
        }
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable int id){ orderservice.deleteOrders(id);}

}
