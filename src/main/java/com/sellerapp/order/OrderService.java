package com.sellerapp.order;

import com.sellerapp.product.ProductRepository;

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
        var orderEntitiesList = orderRepository.findAll();
        return orderDTO.ConvertToResponseViewList(orderEntitiesList);
    }

    public OrderResponseView getOrdersById(int id){
        return orderDTO.ConvertToResponseView(orderRepository.findById(id).get());
    }

    public Set<OrderResponseView> getOrdersBySeller(int sid) {
        List<OrderEntity> orders = orderRepository.findBySellerEntitySellerId(sid);
        Set<OrderResponseView> order = new HashSet<OrderResponseView>(orderDTO.ConvertToResponseViewList(orders));
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
