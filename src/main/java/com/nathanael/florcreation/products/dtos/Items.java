package com.nathanael.florcreation.products.dtos;

import com.nathanael.florcreation.products.models.ItemsTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class Items {

    @NotNull
    @Length(min = 1, max = 5)
    private String itemCode;

    @NotNull
    @Length(min = 1, max = 20)
    private String itemName;

    @NotNull
    private Double itemPrice;

    @Length(max = 255)
    private String itemImage;

    private Boolean isAddon;

    @Length(min = 1, max = 5)
    private String discountCode;

    public Items(ItemsTable itemsTable) {
        itemCode = itemsTable.getItemCode();
        itemName = itemsTable.getItemName();
        itemPrice = itemsTable.getItemPrice();
        itemImage = itemsTable.getItemImage();
        isAddon = itemsTable.getIsAddon();

        if (itemsTable.getDiscount() != null)
            discountCode = itemsTable.getDiscount().getDiscountCode();
    }
}
