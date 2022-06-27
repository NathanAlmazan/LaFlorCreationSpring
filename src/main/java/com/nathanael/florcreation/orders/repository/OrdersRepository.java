package com.nathanael.florcreation.orders.repository;

import com.nathanael.florcreation.orders.models.OrdersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersTable, String> {

    @Query(value = "SELECT * FROM orders_table ORDER BY delivery_date", nativeQuery = true)
    List<OrdersTable> findAllOrders();

    @Query(value = "SELECT * FROM orders_table WHERE order_uid = :uid", nativeQuery = true)
    OrdersTable findByOrderUid(@Param("uid") String uid);

    @Query(value = "SELECT * FROM orders_table WHERE client_id = :client", nativeQuery = true)
    List<OrdersTable> findOrdersByClient(@Param("client") Long clientId);

    @Query(value = "WITH search_client AS (SELECT client_id FROM client_table " +
            "WHERE account_uid = :account), " +
            "insert_recipient AS (INSERT INTO recipient_table (recipient_name, recipient_contact, recipient_street, recipient_city, recipient_province, latitude, longitude) " +
            "VALUES (:rName, :rContact, :rStreet, :rCity, :rProvince, :lat, :lng) RETURNING recipient_id) " +
            "INSERT INTO orders_table (delivery_date, delivery_time, mop, order_message, status, client_id, recipient_id, rider_id, delivery_notes, florist_remarks) " +
            "VALUES (:date, :time, :mop, :message, :status, (SELECT client_id FROM search_client), (SELECT recipient_id FROM insert_recipient), :rider, :dNotes, :fRemarks) " +
            "RETURNING *", nativeQuery = true)
    OrdersTable createOrder(
            @Param("account") String accountUid,
            @Param("rName") String recipientName,
            @Param("rContact") String recipientContact,
            @Param("rStreet") String recipientStreet,
            @Param("rCity") String recipientCity,
            @Param("rProvince") String recipientProvince,
            @Param("lat") Double latitude,
            @Param("lng") Double longitude,
            @Param("date") LocalDate deliveryDate,
            @Param("time") LocalTime deliveryTime,
            @Param("mop") String modeOfPayment,
            @Param("message") String message,
            @Param("status") String status,
            @Param("rider") Long riderId,
            @Param("dNotes") String notes,
            @Param("fRemarks") String remarks
    );
}
