package com.nathanael.florcreation.users.repository;

import com.nathanael.florcreation.users.models.RiderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RiderRepository extends JpaRepository<RiderTable, Long> {

    @Query(value = "SELECT * FROM rider_table ORDER BY rider_id", nativeQuery = true)
    List<RiderTable> findAllRider();

    @Query(value = "SELECT * FROM rider_table WHERE rider_id = :id", nativeQuery = true)
    RiderTable findRiderById(@Param("id") Long riderId);
}
