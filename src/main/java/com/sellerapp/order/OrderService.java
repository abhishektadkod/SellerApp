package com.sellerapp.order;

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

    private OrderResponse orderResponse = new OrderResponse();

    public List<OrderResponse> getOrders() {
        var orders = orderRepository.findAll();
        return orderResponse.getOrderResponse(orders);
    }

    public Optional<Orders> getOrdersById(int id){
        return orderRepository.findById(id);
    }

    public Set<OrderResponse> getOrdersBySeller(int sid) {
        List<Orders> orders = orderRepository.findBySellerSid(sid);
        Set<OrderResponse> order = new HashSet<OrderResponse>(orderResponse.getOrderResponse(orders));
        return order;
    }

    public void addOrders(Orders orders) {
        orderRepository.save(orders);
        for(OrderItems item : orders.getOrderItems()) {
            item.setOrders(orders);
            orderItemsRepository.save(item);
        }
    }

    public void updateOrdersById(Orders orders, int oid) {
        Optional<Orders> order = orderRepository.findById(oid);
        order.get().setStatus(orders.getStatus());
        orderRepository.save(order.get());
    }

    public void deleteOrders(int id){
        orderRepository.deleteById(id);
    }

}
