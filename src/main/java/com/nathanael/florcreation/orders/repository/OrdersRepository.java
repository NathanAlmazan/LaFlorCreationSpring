package com.nathanael.florcreation.orders.repository;

import com.nathanael.florcreation.orders.models.OrdersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional
public interface OrdersRepository extends JpaRepository<OrdersTable, String> {

    @Query(value = "SELECT * FROM orders_table WHERE status != 'CRT' ORDER BY delivery_date DESC", nativeQuery = true)
    List<OrdersTable> findAllOrders();

    @Query(value = "SELECT * FROM orders_table WHERE order_uid = :uid", nativeQuery = true)
    OrdersTable findByOrderUid(@Param("uid") String uid);

    @Query(value = "SELECT SUM(d.quantity * i.itemPrice) AS orderAmount, SUM(s.discountAmount) AS orderDiscount " +
            "FROM OrderDetailsTable d " +
            "INNER JOIN ItemsTable i ON d.id.itemCode = i.itemCode " +
            "LEFT JOIN DiscountTable s ON d.discount.discountCode = s.discountCode " +
            "WHERE d.id.orderUid = :uid")
    OrderAmountProj getOrderAmount(@Param("uid") String uid);

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

    @Query(value = "WITH search_client AS (SELECT client_id FROM client_table " +
            "WHERE account_uid = :account), " +
            "update_recipient AS (UPDATE recipient_table SET recipient_contact = :rContact, recipient_street = :rStreet, recipient_city = :rCity, recipient_province = :rProvince, latitude = :lat, longitude = :lng " +
            "WHERE recipient_id = :recipientId RETURNING recipient_id) " +
            "INSERT INTO orders_table (delivery_date, delivery_time, mop, order_message, status, client_id, recipient_id, rider_id, delivery_notes, florist_remarks) " +
            "VALUES (:date, :time, :mop, :message, :status, (SELECT client_id FROM search_client), (SELECT recipient_id FROM update_recipient), :rider, :dNotes, :fRemarks) " +
            "RETURNING *", nativeQuery = true)
    OrdersTable createOrderWithRecipient(
            @Param("account") String accountUid,
            @Param("recipientId") Long recipientId,
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

    @Query(value = "UPDATE orders_table SET source_id = :source, mop = :type WHERE order_uid = :uid RETURNING *", nativeQuery = true)
    OrdersTable addPaymentSource(
            @Param("uid") String orderUid,
            @Param("source") String sourceId,
            @Param("type") String modeOfPayment
    );

    @Query(value = "WITH search_order AS (SELECT source_id FROM orders_table WHERE order_uid = :uid) " +
            "SELECT * FROM orders_table WHERE source_id = (SELECT source_id FROM search_order)", nativeQuery = true)
    List<OrdersTable> findOrdersByPaymentSource(@Param("uid") String orderUid);

    @Query(value = "UPDATE orders_table SET status = :status WHERE order_uid IN :orders RETURNING order_uid", nativeQuery = true)
    List<OrdersTable> updateOrdersStatus(@Param("orders") List<String> orders, @Param("status") String status);

    @Query(value = "SELECT COUNT(*) AS orderCount FROM OrdersTable WHERE client.clientId = :client AND status='PND'")
    AccountOrderProj getUnpaidOrderCount(@Param("client") Long clientId);

    @Modifying
    @Query(value = "DELETE FROM orders_table WHERE order_uid = :order", nativeQuery = true)
    void deleteOrder(@Param("order") String orderUid);

    @Query(value = "UPDATE orders_table SET rider_id = :riderId, status = 'DSP' WHERE order_uid = :orderUid RETURNING *", nativeQuery = true)
    OrdersTable setOrderRider(@Param("riderId") Long riderId, @Param("orderUid") String orderUid);

    @Query(value = "UPDATE orders_table SET status = 'DLV' WHERE order_uid = :uid RETURNING *", nativeQuery = true)
    OrdersTable setOrderDelivered(@Param("uid") String orderUid);

    @Query(value = "SELECT r.recipientProvince AS province, COUNT(*) AS orderCount " +
            "FROM OrdersTable o, RecipientTable r " +
            "WHERE o.recipient.recipientId = r.recipientId " +
            "GROUP BY r.recipientProvince")
    List<ProvinceRankingProj> getProvinceRanking();

    @Query(value = "SELECT deliveryDate AS delivery, COUNT(order_uid) AS orderCount " +
            "FROM OrdersTable " +
            "GROUP BY deliveryDate " +
            "ORDER BY deliveryDate DESC")
    List<DailyOrderProj> getDailyOrders();
}
