package com.nathanael.florcreation.users.repository;

import com.nathanael.florcreation.users.models.RecipientTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipientRepository extends JpaRepository<RecipientTable, Long> {

    @Query(value = "SELECT * FROM recipient_table WHERE recipient_id = :id", nativeQuery = true)
    RecipientTable findByRecipientId(@Param("id") Long recipientId);

    @Query(value = "WITH search_client AS (SELECT client_id FROM client_table " +
            "WHERE account_uid = :account) " +
            "SELECT r.recipient_id, r.recipient_name, r.recipient_contact, r.recipient_street, r.recipient_city, r.recipient_province, r.latitude, r.longitude " +
            "FROM orders_table o " +
            "INNER JOIN recipient_table r " +
            "ON r.recipient_id = o.recipient_id " +
            "WHERE o.client_id = (SELECT client_id FROM search_client)", nativeQuery = true)
    List<RecipientTable> getClientRecipients(@Param("account") String account);
}
