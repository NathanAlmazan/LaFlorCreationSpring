package com.nathanael.florcreation.products.repository;

import com.nathanael.florcreation.products.models.DiscountTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DiscountRepository extends JpaRepository<DiscountTable, String> {

    @Query(value = "SELECT * FROM discount_table ORDER BY discount_code", nativeQuery = true)
    List<DiscountTable> findAllDiscount();

    @Query(value = "SELECT * FROM discount_table WHERE discount_code = :code", nativeQuery = true)
    DiscountTable findByDiscountCode(@Param("code") String code);

    @Query(value = "INSERT INTO discount_table (discount_code, discount_amount) " +
            "VALUES (:code, :amount) RETURNING *", nativeQuery = true)
    DiscountTable createDiscount(
            @Param("code") String code,
            @Param("amount") Double amount
    );

    @Modifying
    @Query(value = "DELETE FROM discount_table WHERE discount_code = :code", nativeQuery = true)
    void deleteDiscount(@Param("code") String discCode);
}
