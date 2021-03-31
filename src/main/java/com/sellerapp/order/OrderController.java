package com.sellerapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<OrderResponse> getOrdersS(){
        return orderservice.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<Orders> getOrdersId(@PathVariable int id){
        return orderservice.getOrdersById(id);
    }

    @GetMapping("/orders/seller")
    public Set<OrderResponse> getOrdersBySeller(HttpServletRequest request){
        int sid =(Integer) request.getAttribute("sid");
        return  orderservice.getOrdersBySeller(sid);}

    @PostMapping(value="/orders")
    public ResponseEntity<String> addOrders (@Validated @RequestBody Orders Orders) throws Exception {
        orderservice.addOrders(Orders);
        return ResponseEntity.ok("Order Added Successfully!");

    }

    @PutMapping("/orders/{id}")
    public void updateOrdersById(@RequestBody Orders Orders, @PathVariable int id){ orderservice.updateOrdersById(Orders,id);}

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable int id){ orderservice.deleteOrders(id);}

}
