package com.nathanael.florcreation.products;

import com.nathanael.florcreation.errors.EntityExceptions;
import com.nathanael.florcreation.products.dtos.Discount;
import com.nathanael.florcreation.products.dtos.ItemRankDto;
import com.nathanael.florcreation.products.dtos.Items;
import com.nathanael.florcreation.products.mappers.ProductMapper;
import com.nathanael.florcreation.products.models.DiscountTable;
import com.nathanael.florcreation.products.models.ItemsTable;
import com.nathanael.florcreation.products.repository.DiscountRepository;
import com.nathanael.florcreation.products.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServices {
    @Autowired private ItemsRepository itemsRepository;
    @Autowired private DiscountRepository discountRepository;
    @Autowired private ProductMapper productMapper;

    public List<Items> getAllItems(Boolean addon) {
        if (addon == null)
            return productMapper.itemsTableToItemsList(
                    itemsRepository.findAllItems()
            );

        return productMapper.itemsTableToItemsList(
                itemsRepository.findAllIFilteredItems(addon)
        );
    }

    public Items getItemByCode(String code) {
        ItemsTable item = itemsRepository.findByItemCode(code);
        if (item == null) throw new EntityExceptions("Items", code + " item not found");
        return productMapper.itemsTableToItems(item);
    }

    public List<Items> getItemByDiscountCode(String code) {
        return productMapper.itemsTableToItemsList(
                itemsRepository.findByDiscountCode(code)
        );
    }

    public Items addNewItem(Items newItem) {
        if (itemsRepository.findByItemCode(newItem.getItemCode()) != null)
            return updateItem(newItem);

        return productMapper.itemsTableToItems(
                itemsRepository.createItem(
                        newItem.getItemCode(),
                        newItem.getItemName(),
                        newItem.getItemPrice(),
                        newItem.getIsAddon(),
                        newItem.getItemImage(),
                        newItem.getDiscountCode()
                )
        );
    }

    public Items updateItem(Items item) {
        if (itemsRepository.findByItemCode(item.getItemCode()) == null)
            throw new EntityExceptions("Items", item.getItemCode() + " item not found.");

        return productMapper.itemsTableToItems(
                itemsRepository.updateItem(
                        item.getItemCode(),
                        item.getItemName(),
                        item.getItemPrice(),
                        item.getIsAddon(),
                        item.getItemImage(),
                        item.getDiscountCode()
                )
        );
    }

    public Items deleteItem(String itemCode) {
        Items deletedItem = productMapper.itemsTableToItems(
                itemsRepository.findByItemCode(itemCode)
        );

        if (deletedItem == null) throw new EntityExceptions("Items", "Item does not exist");
        itemsRepository.deleteItem(itemCode);

        return deletedItem;
    }

    public List<Discount> getAllDiscount() {
        return productMapper.discountTableToDiscountList(
                discountRepository.findAllDiscount()
        );
    }

    public Discount getDiscountByCode(String code) {
        return productMapper.discountTableToDiscount(
                discountRepository.findByDiscountCode(code)
        );
    }

    public Discount addNewDiscount(Discount newDiscount) {
        return productMapper.discountTableToDiscount(
                discountRepository.createDiscount(
                        newDiscount.getDiscCode(),
                        newDiscount.getDiscAmount()
                )
        );
    }

    public Discount deleteDiscount(String discCode) {
        DiscountTable exists = discountRepository.findByDiscountCode(discCode);

        if (exists == null) throw new EntityExceptions("Discount", "Discount not found.");

        discountRepository.deleteDiscount(discCode);

        return productMapper.discountTableToDiscount(exists);
    }

    public List<ItemRankDto> getItemRanks() {
        return productMapper.itemRankToDtoList(
                itemsRepository.getItemRanks()
        );
    }
}
