package com.nathanael.florcreation.products.repository;

import com.nathanael.florcreation.products.models.ItemsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemsRepository extends JpaRepository<ItemsTable, String> {

    @Query(value = "SELECT * FROM items_table ORDER BY item_code", nativeQuery = true)
    List<ItemsTable> findAllItems();

    @Query(value = "SELECT * FROM items_table WHERE is_addon = :addon ORDER BY item_code", nativeQuery = true)
    List<ItemsTable> findAllIFilteredItems(@Param("addon") Boolean addon);

    @Query(value = "SELECT * FROM items_table WHERE item_code = :code", nativeQuery = true)
    ItemsTable findByItemCode(@Param("code") String code);

    @Query(value = "SELECT * FROM items_table WHERE discount_code = :code", nativeQuery = true)
    List<ItemsTable> findByDiscountCode(@Param("code") String code);

    @Query(value = "INSERT INTO items_table (item_code, item_name, item_price, is_addon, item_image, discount_code) " +
            "VALUES (:code, :name, :price, :addon, :image, :discount) RETURNING *", nativeQuery = true)
    ItemsTable createItem(
            @Param("code") String code,
            @Param("name") String name,
            @Param("price") Double price,
            @Param("addon") Boolean addon,
            @Param("image") String image,
            @Param("discount") String discCode
    );

    @Query(value = "UPDATE items_table SET item_name = :name, item_price = :price, is_addon = :addon, " +
            "item_image = :image, discount_code = :discount WHERE item_code = :code RETURNING *", nativeQuery = true)
    ItemsTable updateItem(
            @Param("code") String code,
            @Param("name") String name,
            @Param("price") Double price,
            @Param("addon") Boolean addon,
            @Param("image") String image,
            @Param("discount") String discCode
    );
}
