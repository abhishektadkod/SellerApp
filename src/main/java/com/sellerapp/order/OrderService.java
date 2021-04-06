package com.sellerapp.order;

import com.sellerapp.customer.Customer;
import com.sellerapp.product.Product;
import com.sellerapp.product.ProductRepository;
import com.sellerapp.seller.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDTO orderDTO;

    public List<OrderResponseView> getOrders() {
        var orders = orderRepository.findAll();
        return orderDTO.ConvertToResponseView(orders);
    }

    public Optional<OrderEntity> getOrdersById(int id){
        return orderRepository.findById(id);
    }

    public Set<OrderResponseView> getOrdersBySeller(int sid) {
        List<OrderEntity> orders = orderRepository.findBySellerEntitySellerId(sid);
        Set<OrderResponseView> order = new HashSet<OrderResponseView>(orderDTO.ConvertToResponseView(orders));
        return order;
    }

    public void addOrders(OrderRequestView orderRequestView) {
        OrderEntity order = orderDTO.ConvertToOrderEntity(orderRequestView);
        orderRepository.save(order);
    }

    public void updateOrdersById(OrderEntity orderEntity, int oid) {
        Optional<OrderEntity> order = orderRepository.findById(oid);
        order.get().setStatus(orderEntity.getStatus());
        orderRepository.save(order.get());
    }

    public void deleteOrders(int id){
        orderRepository.deleteById(id);
    }

}
