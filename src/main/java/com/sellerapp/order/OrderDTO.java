package com.sellerapp.order;

import com.sellerapp.product.ProductEntity;
import com.sellerapp.product.ProductRepository;
import com.sellerapp.seller.SellerDTO;
import com.sellerapp.seller.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderDTO {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerDTO sellerDTO;

    public OrderEntity ConvertToOrderEntity(OrderRequestView orderRequestView) {
        List<OrderItemsEntity> orderItemEntities = new ArrayList<OrderItemsEntity>();

        float totalPrice = 0;
        float orderPreparationTime = 0;

        for (OrderItemsRequestView item : orderRequestView.getOrderItems()) {
            OrderItemsEntity newItem = new OrderItemsEntity();
            ProductEntity productEntity = productRepository.findById(item.getProductsId()).get();    //Get Product Information from from Products DAO for Order Items

            newItem.setItemId(item.getItemId());
            newItem.setProductName(productEntity.getName());
            newItem.setSkuId(productEntity.getSku_id());
            newItem.setBasic_etc(productEntity.getBasic_eta());
            newItem.setPrice(productEntity.getPrice());
            newItem.setQuantity(item.getQuantity());
            newItem.setDescription(productEntity.getDescription());
            newItem.setImage(productEntity.getImage());
            newItem.setUpc(productEntity.getUpc());

            orderItemEntities.add(newItem);
            totalPrice += item.getQuantity() * productEntity.getPrice();
            orderPreparationTime += item.getQuantity() * newItem.getBasic_etc();
        }

        return OrderEntity.builder().
                sellerEntity(SellerEntity.                          //To Join Seller primary key to Orders
                        builder().
                        sellerId(orderRequestView.getSellerId()).
                        build()).
                customer(orderRequestView.getCustomer()).
                deliveryResource(new DeliveryResourceEntity()).
                status(orderRequestView.getStatus()).
                businessUnit(orderRequestView.getBusinessUnit()).
                orderFulfillmentTime(orderRequestView.getOrderFulfillmentTime()).
                orderItemEntities(orderItemEntities).
                totalPrice(totalPrice).
                orderPreparationTime(orderPreparationTime).
                build();
    }

    public Date ConvertDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.getTime());
    }

    public OrderSellerResponseView ConvertToSellerResponseView(OrderEntity orders) {
        OrderSellerResponseView orderResponseItem = new OrderSellerResponseView();
        orderResponseItem.setOrderId(orders.getOrderId());
        orderResponseItem.setCustomer(orders.getCustomer());
        orderResponseItem.setDeliveryResource(orders.getDeliveryResource());
        orderResponseItem.setOrderPlacedDate(ConvertDate(orders.getOrderDate()));
        orderResponseItem.setBusinessUnit(orders.getBusinessUnit());
        orderResponseItem.setStatus(orders.getStatus());
        orderResponseItem.setTotalPrice(orders.getTotalPrice());
        orderResponseItem.setOrderFulfillmentTime(orders.getOrderFulfillmentTime());
        orderResponseItem.setOrderPreparationTime(orders.getOrderPreparationTime());
        orderResponseItem.setOrderItems(orders.getOrderItemEntities());
        return orderResponseItem;
    }

    public OrderAdminResponseView ConvertToAdminResponseView(OrderEntity orders) {
        OrderAdminResponseView orderResponseItem = new OrderAdminResponseView();
        orderResponseItem.setOrderId(orders.getOrderId());
        orderResponseItem.setCustomer(orders.getCustomer());
        orderResponseItem.setDeliveryResource(orders.getDeliveryResource());
        orderResponseItem.setSeller(sellerDTO.ConvertToResponseView(orders.getSellerEntity()));
        orderResponseItem.setOrderPlacedDate(ConvertDate(orders.getOrderDate()));
        orderResponseItem.setBusinessUnit(orders.getBusinessUnit());
        orderResponseItem.setStatus(orders.getStatus());
        orderResponseItem.setTotalPrice(orders.getTotalPrice());
        orderResponseItem.setOrderFulfillmentTime(orders.getOrderFulfillmentTime());
        orderResponseItem.setOrderPreparationTime(orders.getOrderPreparationTime());
        orderResponseItem.setOrderItems(orders.getOrderItemEntities());
        return orderResponseItem;
    }

    public List<OrderResponseView> ConvertToResponseViewList(List<OrderEntity> orderEntities, int type) {
        List<OrderResponseView> orderResponseViews = new ArrayList<>();
        for (OrderEntity orders : orderEntities) {
            if(type==0) {                                                       //TYPE=0 --> Order Response for Seller
                orderResponseViews.add(ConvertToSellerResponseView(orders));
            }
            else {                                                              //TYPE=0 --> Order Response for Admin
                orderResponseViews.add(ConvertToAdminResponseView(orders));
            }
        }
        return orderResponseViews;
    }

}
