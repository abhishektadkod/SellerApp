package com.sellerapp.order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer> {
    List<OrderItems> findByProductsPid(int pid);
}
