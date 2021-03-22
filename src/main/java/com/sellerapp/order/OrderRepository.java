package com.sellerapp.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

   /* @Query(value="select o.oid,c.name as customer, s.name as seller,o.datetime,o.source,o.status from orders o\n" +
            "INNER JOIN customer c ON o.cid = c.cid\n" +
            "INNER JOIN seller s ON o.sid = s.sid;\n",nativeQuery = true)
    List<Orders> findAll();*/
}
