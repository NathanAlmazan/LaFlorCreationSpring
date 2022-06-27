package com.nathanael.florcreation.products.models;

import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DiscountTable")
@NoArgsConstructor
@Setter
@Getter
public class DiscountTable {
    @Id
    @Column(name = "discount_code", nullable = false, unique = true, length = 5)
    private String discountCode;

    @Column(name = "discount_amount", nullable = false)
    private Double discountAmount;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private List<ItemsTable> discountedItems;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private List<OrderDetailsTable> discountedOrders;
}
