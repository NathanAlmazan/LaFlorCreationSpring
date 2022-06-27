package com.nathanael.florcreation.products.models;

import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ItemsTable")
@NoArgsConstructor
@Setter
@Getter
public class ItemsTable {
    @Id
    @Column(name = "item_code", nullable = false, unique = true, length = 5)
    private String itemCode;

    @Column(name = "item_name", nullable = false, length = 20)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private Double itemPrice;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "is_addon")
    private Boolean isAddon = false;

    @ManyToOne
    @JoinColumn(name = "discount_code", referencedColumnName = "discount_code")
    private DiscountTable discount;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderDetailsTable> itemsOrdered;
}
