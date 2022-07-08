package com.nathanael.florcreation.products.dtos;

import com.nathanael.florcreation.products.repository.ItemRank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemRankDto {
    private String itemCode;
    private Double itemSales;

    public ItemRankDto(ItemRank itemRank) {
        itemCode = itemRank.getItem();
        itemSales = itemRank.getItemSales();
    }
}
