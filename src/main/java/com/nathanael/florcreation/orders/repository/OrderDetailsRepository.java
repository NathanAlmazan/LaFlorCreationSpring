package com.nathanael.florcreation.orders.repository;

import com.nathanael.florcreation.orders.models.OrderDetailsKey;
import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsTable, OrderDetailsKey> {

    @Query(value = "INSERT INTO order_details_table (item_code, order_uid, quantity, discount_code) " +
            "VALUES (:code, :uid, :quantity, :discount) RETURNING *", nativeQuery = true)
    OrderDetailsTable createOrderDetail(
            @Param("code") String code,
            @Param("uid") String orderUid,
            @Param("quantity") Integer quantity,
            @Param("discount") String discountCode
    );

    @Query(value = "SELECT * FROM order_details_table WHERE order_uid = :order ORDER BY item_code", nativeQuery = true)
    List<OrderDetailsTable> getByOrderUid(@Param("order") String orderUid);
}
