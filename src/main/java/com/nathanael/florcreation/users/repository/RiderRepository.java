package com.nathanael.florcreation.users.repository;

import com.nathanael.florcreation.users.models.RiderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RiderRepository extends JpaRepository<RiderTable, Long> {

    @Query(value = "SELECT * FROM rider_table ORDER BY rider_id", nativeQuery = true)
    List<RiderTable> findAllRider();

    @Query(value = "SELECT * FROM rider_table WHERE rider_name = :name", nativeQuery = true)
    RiderTable findRiderByName(@Param("name") String name);

    @Query(value = "SELECT * FROM rider_table WHERE rider_id = :id", nativeQuery = true)
    RiderTable findRiderById(@Param("id") Long riderId);

    @Query(value = "INSERT INTO rider_table (rider_name, rider_contact, rider_city, rider_province) " +
            "VALUES (:riderName, :riderContact, :riderCity, :riderProvince) RETURNING *", nativeQuery = true)
    RiderTable createNewRider(
            @Param("riderName") String riderName,
            @Param("riderContact") String riderContact,
            @Param("riderCity") String riderCity,
            @Param("riderProvince") String riderProvince
    );

    @Query(value = "UPDATE rider_table SET rider_name = :riderName, rider_contact = :riderContact, rider_city = :riderCity, " +
            "rider_province = :riderProvince, rider_image = :riderImage, account_uid = :riderAccount " +
            "WHERE rider_id = :riderId RETURNING *", nativeQuery = true)
    RiderTable updateRider(
            @Param("riderId") Long riderId,
            @Param("riderName") String riderName,
            @Param("riderContact") String riderContact,
            @Param("riderCity") String riderCity,
            @Param("riderProvince") String riderProvince,
            @Param("riderImage") String riderImage,
            @Param("riderAccount") String accountUid
    );

    @Modifying
    @Query(value = "DELETE FROM rider_table WHERE rider_id = :id", nativeQuery = true)
    void deleteRider(@Param("id") Long riderId);

    @Query(value = "SELECT * FROM rider_table WHERE rider_city ILIKE :city AND rider_province ILIKE :province", nativeQuery = true)
    List<RiderTable> findByRiderArea(@Param("city") String city, @Param("province") String province);
}
