package com.nathanael.florcreation.products.mappers;

import com.nathanael.florcreation.products.dtos.Discount;
import com.nathanael.florcreation.products.dtos.ItemRankDto;
import com.nathanael.florcreation.products.dtos.Items;
import com.nathanael.florcreation.products.models.DiscountTable;
import com.nathanael.florcreation.products.models.ItemsTable;
import com.nathanael.florcreation.products.repository.ItemRank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    public Items itemsTableToItems(ItemsTable itemsTable) {
        return new Items(itemsTable);
    }

    public List<Items> itemsTableToItemsList(List<ItemsTable> itemsTables) {
        List<Items> itemList = new ArrayList<>(itemsTables.size());

        for (ItemsTable item : itemsTables)
            itemList.add(itemsTableToItems(item));

        return itemList;
    }

    public Discount discountTableToDiscount(DiscountTable discountTable) {
        return new Discount(discountTable);
    }

    public List<Discount> discountTableToDiscountList(List<DiscountTable> discountTables) {
        List<Discount> discountList = new ArrayList<>(discountTables.size());

        for (DiscountTable discount : discountTables)
            discountList.add(discountTableToDiscount(discount));

        return discountList;
    }

    public ItemRankDto itemRankToDto(ItemRank itemRank) {
        return new ItemRankDto(itemRank);
    }

    public List<ItemRankDto> itemRankToDtoList(List<ItemRank> itemRanks) {
        List<ItemRankDto> itemRankList = new ArrayList<>(itemRanks.size());

        for (ItemRank item : itemRanks)
            itemRankList.add(itemRankToDto(item));

        return itemRankList;
    }
}
