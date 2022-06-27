package com.nathanael.florcreation.orders.models;

import com.nathanael.florcreation.products.models.DiscountTable;
import com.nathanael.florcreation.products.models.ItemsTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetailsTable")
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailsTable {
    @EmbeddedId
    private OrderDetailsKey id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @MapsId("orderUid")
    @JoinColumn(name = "order_uid")
    private OrdersTable order;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "item_code")
    private ItemsTable item;

    @ManyToOne
    @JoinColumn(name = "discount_code", referencedColumnName = "discount_code")
    private DiscountTable discount;
}
