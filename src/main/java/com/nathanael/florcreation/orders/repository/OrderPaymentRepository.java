package com.nathanael.florcreation.orders.repository;

import com.nathanael.florcreation.orders.models.OrderPaymentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderPaymentRepository extends JpaRepository<OrderPaymentTable, String> {

    @Query(value = "INSERT INTO order_payment_table (source_id, callback_url) " +
            "VALUES (:source, :url) RETURNING *", nativeQuery = true)
    OrderPaymentTable createPaymentSource(
            @Param("source") String source,
            @Param("url") String callbackUrl
    );

    @Query(value = "UPDATE order_payment_table SET payment_id = :id, payment_date = :date " +
            "WHERE source_id = :source RETURNING *", nativeQuery = true)
    OrderPaymentTable confirmPayment(
            @Param("source") String sourceId,
            @Param("id") String paymentId,
            @Param("date") LocalDate paymentDate

    );

    @Query(value = "SELECT * FROM order_payment_table WHERE source_id = :source", nativeQuery = true)
    OrderPaymentTable findOrderPaymentById(@Param("source") String sourceId);
}
