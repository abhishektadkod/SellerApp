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

    private OrderResponse orderResponse = new OrderResponse();

    public List<OrderResponse> getOrders() {
        var orders = orderRepository.findAll();
        return orderResponse.getOrderResponse(orders);
    }

    public Optional<Orders> getOrdersById(int id){
        return orderRepository.findById(id);
    }

    public Set<OrderResponse> getOrdersBySeller(int sid) {
        List<Orders> orders = orderRepository.findBySellerEntitySellerId(sid);
        Set<OrderResponse> order = new HashSet<OrderResponse>(orderResponse.getOrderResponse(orders));
        return order;
    }

    public void addOrders(OrderRequestView orders) {
        Orders order = new Orders();
        Customer customer = new Customer();
        SellerEntity sellerEntity = new SellerEntity();
        List<OrderItems> orderItems = new ArrayList<OrderItems>();
        float totalPrice=0;

        customer.setCid(orders.getCid());
        sellerEntity.setSellerId(orders.getSid());
        order.setCustomer(customer);
        order.setSellerEntity(sellerEntity);
        order.setStatus(orders.getStatus());
        order.setSource(orders.getSource());

        for(OrderItemsRequestView item: orders.getOrderItems()){
            OrderItems newItem = new OrderItems();
            Product product = productRepository.findById(item.getProductsId()).get();
            newItem.setItemId(item.getItemId());
            newItem.setProductName(product.getName());
            newItem.setSkuId(product.getSku_id());
            newItem.setOrderCompletionTime(product.getBasic_eta());
            newItem.setPrice(product.getPrice());
            newItem.setQuantity(item.getQuantity());
            orderItems.add(newItem);
            totalPrice+= item.getQuantity()*product.getPrice();
        }

        order.setOrderItems(orderItems);

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
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
