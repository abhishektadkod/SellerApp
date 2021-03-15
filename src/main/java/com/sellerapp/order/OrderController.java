package com.sellerapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<Orders> getOrdersS(){
        return orderservice.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<Orders> getOrdersId(@PathVariable int id){
        return orderservice.getOrdersById(id);
    }

    @GetMapping("/orders/seller/{id}")
    public Set<Orders> getOrdersBySeller(@PathVariable int id){ return  orderservice.getOrdersBySeller(id);}


    @PostMapping(value="/orders")
    public void addOrders(@Validated @RequestBody Orders Orders){
        orderservice.addOrders(Orders);
    }

    @PutMapping("/orders")
    public void updateOrders(@RequestBody Orders Orders){ orderservice.updateOrders(Orders);}

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable int id){ orderservice.deleteOrders(id);}

}
