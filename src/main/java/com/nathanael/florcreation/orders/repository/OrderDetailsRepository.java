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

    @Query(value = "SELECT (d.quantity * i.itemPrice) AS totalPrice, s AS orderDiscount, d AS orderDetail " +
            "FROM OrderDetailsTable d INNER JOIN ItemsTable i ON d.id.itemCode = i.itemCode " +
            "LEFT JOIN DiscountTable s ON d.discount.discountCode = s.discountCode " +
            "WHERE d.id.orderUid = :order AND d.id.itemCode = :code")
    OrderDetailsProj getByOrderDetailKey(@Param("order") String orderUid, @Param("code") String code);

    @Query(value = "SELECT (d.quantity * i.itemPrice) AS totalPrice, s AS orderDiscount, d AS orderDetail " +
            "FROM OrderDetailsTable d INNER JOIN ItemsTable i ON d.id.itemCode = i.itemCode " +
            "LEFT JOIN DiscountTable s ON d.discount.discountCode = s.discountCode " +
            "WHERE d.id.orderUid = :order")
    List<OrderDetailsProj> getByOrderUid(@Param("order") String orderUid);
}
