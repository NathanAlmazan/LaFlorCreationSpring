package com.nathanael.florcreation.users.repository;

import com.nathanael.florcreation.users.models.RecipientTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipientRepository extends JpaRepository<RecipientTable, Long> {

    @Query(value = "SELECT * FROM recipient_table WHERE recipient_id = :id", nativeQuery = true)
    RecipientTable findByRecipientId(@Param("id") Long recipientId);

}
