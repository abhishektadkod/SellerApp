package com.sellerapp.order;

import com.sellerapp.product.ProductEntity;
import com.sellerapp.product.ProductRepository;
import com.sellerapp.seller.SellerDTO;
import com.sellerapp.seller.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDTO {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerDTO sellerDTO;

    public OrderEntity ConvertToOrderEntity(OrderRequestView orderRequestView){
        OrderEntity order = new OrderEntity();
        SellerEntity sellerEntity = new SellerEntity();
        List<OrderItemsEntity> orderItemEntities = new ArrayList<OrderItemsEntity>();
        float totalPrice=0;

        sellerEntity.setSellerId(orderRequestView.getSellerId()); //To Join Seller information to orders

        order.setCustomer(orderRequestView.getCustomer());
        order.setSellerEntity(sellerEntity);
        order.setStatus(orderRequestView.getStatus());
        order.setBusinessUnit(orderRequestView.getBusinessUnit());

        for(OrderItemsRequestView item: orderRequestView.getOrderItems()){
            OrderItemsEntity newItem = new OrderItemsEntity();
            ProductEntity productEntity = productRepository.findById(item.getProductsId()).get();

            newItem.setItemId(item.getItemId());
            newItem.setProductName(productEntity.getName());
            newItem.setSkuId(productEntity.getSku_id());
            newItem.setOrderCompletionTime(productEntity.getBasic_eta());
            newItem.setPrice(productEntity.getPrice());
            newItem.setQuantity(item.getQuantity());
            newItem.setDescription(productEntity.getDescription());
            newItem.setImage(productEntity.getImage());
            newItem.setUpc(productEntity.getUpc());

            orderItemEntities.add(newItem);
            totalPrice+= item.getQuantity()* productEntity.getPrice();
        }

        order.setOrderItemEntities(orderItemEntities);
        order.setTotalPrice(totalPrice);


        return order;
    }

    public OrderResponseView ConvertToResponseView(OrderEntity orders){
        OrderResponseView orderResponseItem = new OrderResponseView();
        orderResponseItem.setOid(orders.getOrderId());
        orderResponseItem.setCustomer(orders.getCustomer());
        orderResponseItem.setSeller(sellerDTO.ConvertToResponseView(orders.getSellerEntity()));
        orderResponseItem.setDate(orders.getOrderDate());
        orderResponseItem.setBusinessUnit(orders.getBusinessUnit());
        orderResponseItem.setStatus(orders.getStatus());
        orderResponseItem.setTotalPrice(orders.getTotalPrice());
        orderResponseItem.setOrderItem(orders.getOrderItemEntities());
        return orderResponseItem;
    }
    
    public List<OrderResponseView> ConvertToResponseViewList(List<OrderEntity> orderEntities){
            List<OrderResponseView> orderResponseViews = new ArrayList<>();
            for(OrderEntity orders:orderEntities){
                orderResponseViews.add(ConvertToResponseView(orders));
            }
            return orderResponseViews;
        
    }
}
