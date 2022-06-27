package com.nathanael.florcreation.users.repository;

import com.nathanael.florcreation.orders.models.OrdersTable;
import com.nathanael.florcreation.users.models.ClientTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientTable, Long> {

    @Query(value = "SELECT * FROM client_table ORDER BY client_id", nativeQuery = true)
    List<ClientTable> findAllClient();

    @Query(value = "SELECT * FROM client_table WHERE client_id = :clientId", nativeQuery = true)
    ClientTable findByClientById(@Param("clientId") Long clientId);

    @Query(value = "SELECT * FROM client_table WHERE account_uid = :account", nativeQuery = true)
    ClientTable findByClientAccount(@Param("account") String accountUid);

    @Query(value = "INSERT INTO client_table (client_name, client_contact, account_uid) " +
            "VALUES (:clientName, :clientContact, :account) RETURNING *",nativeQuery = true)
    ClientTable createClient(
            @Param("clientName") String name,
            @Param("clientContact") String contact,
            @Param("account") String accountUid
    );
}
