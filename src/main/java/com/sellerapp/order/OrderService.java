package com.sellerapp.order;


import com.sellerapp.product.Product;
import com.sellerapp.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Orders> getOrders() {
        List<Orders> Orders = new ArrayList<>();
        orderRepository.findAll().forEach(Orders::add);
        return Orders;
    }

    public Optional<Orders> getOrdersById(int id){
        return orderRepository.findById(id);
    }

    public Set<Orders> getOrdersBySeller(int id) {
        Set<Orders> orders = new HashSet<>();

        for(Product products : productRepository.findBySellerSid(id)) {
            for(OrderItems orderItems : orderItemsRepository.findByProductsPid(products.getPid())){
                orders.add(orderItems.getOrders());
            }
        }
        return orders;
    }

    public void addOrders(Orders orders) {
        Logger logger = Logger.getLogger(OrderService.class.getName());
        logger.log(Level.INFO, "logging: {0} ", orders.getOrderItems().size());

        orderRepository.save(orders);
        List<OrderItems> orderItems = new ArrayList<>();

        for(OrderItems item : orders.getOrderItems()) {
            item.setOrders(orders);
            orderItemsRepository.save(item);
        }
    }

    public void updateOrders(Orders Orders) {
        orderRepository.save(Orders);
    }

    public void deleteOrders(int id){
        orderRepository.deleteById(id);
    }


}
