package com.nathanael.florcreation.products.mappers;

import com.nathanael.florcreation.products.dtos.Discount;
import com.nathanael.florcreation.products.dtos.Items;
import com.nathanael.florcreation.products.models.DiscountTable;
import com.nathanael.florcreation.products.models.ItemsTable;
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
}
