package com.sellerapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<OrderResponseView> getOrdersS(){
        return orderservice.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderEntity> getOrdersId(@PathVariable int id){
        return orderservice.getOrdersById(id);
    }

    @GetMapping("/orders/seller")
    public Set<OrderResponseView> getOrdersBySeller(HttpServletRequest request){
        int sid =(Integer) request.getAttribute("sid");
        return  orderservice.getOrdersBySeller(sid);}

    @PostMapping(value="/orders")
    public ResponseEntity<Map<String,String>> addOrders (@Validated @RequestBody OrderRequestView Orders) throws Exception {
        orderservice.addOrders(Orders);
        Map<String,String> map = new HashMap<>();
        map.put("response","Order Added Successfully!");
        return ResponseEntity.ok(map);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Map<String,String>> updateOrdersById(@RequestBody OrderEntity OrderEntity, @PathVariable int id){
        orderservice.updateOrdersById(OrderEntity,id);
        Map<String,String> map = new HashMap<>();
        map.put("response","Order Updated Successfully!");
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable int id){ orderservice.deleteOrders(id);}

}
