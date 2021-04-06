package com.sellerapp.order;

import com.sellerapp.customer.Customer;
import com.sellerapp.product.Product;
import com.sellerapp.product.ProductRepository;
import com.sellerapp.seller.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDTO {
    @Autowired
    private ProductRepository productRepository;

    public OrderEntity ConvertToOrderEntity(OrderRequestView orderRequestView){
        OrderEntity order = new OrderEntity();
        Customer customer = new Customer();
        SellerEntity sellerEntity = new SellerEntity();
        List<OrderItemsEntity> orderItemEntities = new ArrayList<OrderItemsEntity>();
        float totalPrice=0;

        customer.setCustomerId(orderRequestView.getCid()); //To Join Customer information to orders
        sellerEntity.setSellerId(orderRequestView.getSid()); //To Join Seller information to orders

        order.setCustomer(customer);
        order.setSellerEntity(sellerEntity);
        order.setStatus(orderRequestView.getStatus());
        order.setSource(orderRequestView.getSource());

        for(OrderItemsRequestView item: orderRequestView.getOrderItems()){
            OrderItemsEntity newItem = new OrderItemsEntity();
            Product product = productRepository.findById(item.getProductsId()).get();
            newItem.setItemId(item.getItemId());
            newItem.setProductName(product.getName());
            newItem.setSkuId(product.getSku_id());
            newItem.setOrderCompletionTime(product.getBasic_eta());
            newItem.setPrice(product.getPrice());
            newItem.setQuantity(item.getQuantity());
            orderItemEntities.add(newItem);
            totalPrice+= item.getQuantity()*product.getPrice();
        }

        order.setOrderItemEntities(orderItemEntities);
        order.setTotalPrice(totalPrice);


        return order;
    }
    
    public List<OrderResponseView> ConvertToResponseView(List<OrderEntity> orderEntities){
            List<OrderResponseView> orderResponseViews = new ArrayList<>();
            for(OrderEntity orders:orderEntities){
                OrderResponseView orderResponseItem = new OrderResponseView();
                orderResponseItem.setOid(orders.getOrderId());
                orderResponseItem.setCustomer(orders.getCustomer().getName());
                orderResponseItem.setSeller(orders.getSellerEntity().getName());
                orderResponseItem.setDate(orders.getDate());
                orderResponseItem.setSource(orders.getSource());
                orderResponseItem.setStatus(orders.getStatus());
                orderResponseItem.setTotalPrice(orders.getTotalPrice());
                orderResponseItem.setOrderItem(orders.getOrderItemEntities());
                orderResponseViews.add(orderResponseItem);
            }
            return orderResponseViews;
        
    }
}
