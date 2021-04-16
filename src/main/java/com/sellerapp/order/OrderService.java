package com.sellerapp.order;

import com.sellerapp.DatabaseException;
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
        return orderDTO.ConvertToResponseViewList(orderEntitiesList,0);
    }

    public OrderAdminResponseView getOrdersById(int id) throws DatabaseException{
        try {
            return orderDTO.ConvertToAdminResponseView(orderRepository.findById(id).get());
        }
        catch (NoSuchElementException e){
            throw new DatabaseException("Order Id Invalid");
        }
    }

    public Set<OrderResponseView> getOrdersBySeller(int sid) {
        List<OrderEntity> orders = orderRepository.findBySellerEntitySellerId(sid);
        Set<OrderResponseView> order = new HashSet<OrderResponseView>(orderDTO.ConvertToResponseViewList(orders,0));
        return order;
    }

    public void addOrders(OrderRequestView orderRequestView) throws DatabaseException{
        try {
            OrderEntity order = orderDTO.ConvertToOrderEntity(orderRequestView);
            orderRepository.save(order);
        }
        catch(NoSuchElementException e){
            throw new DatabaseException("Product does not exist");
        }
    }

    public void updateOrdersById(OrderEntity orderEntity, int oid) throws DatabaseException{
        try {
            Optional<OrderEntity> order = orderRepository.findById(oid);
            order.get().setStatus(orderEntity.getStatus());
            orderRepository.save(order.get());
        }
        catch(NoSuchElementException e){
            throw new DatabaseException("Order id doesn't exist");
        }
    }

    public void addDeliveryResource(OrderEntity orderEntity , int oid) throws DatabaseException {
        try{
            Optional<OrderEntity> order = orderRepository.findById(oid);
            order.get().setDeliveryResource(orderEntity.getDeliveryResource());
            orderRepository.save(order.get());
        }
        catch(NoSuchElementException e){
            throw new DatabaseException("Order id doesn't exist");
        }

    }

    public void deleteOrders(int id){
        orderRepository.deleteById(id);
    }


}
