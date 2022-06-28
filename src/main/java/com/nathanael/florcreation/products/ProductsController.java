package com.nathanael.florcreation.products;

import com.nathanael.florcreation.orders.dtos.OrderDetails;
import com.nathanael.florcreation.products.dtos.Discount;
import com.nathanael.florcreation.products.dtos.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductsController {
    @Autowired private ProductsServices services;

    @QueryMapping
    public List<Items> allItems(@Argument("addons") Boolean addons) {
        return services.getAllItems(addons);
    }

    @QueryMapping
    public Items itemByCode(@Argument String code) {
        return services.getItemByCode(code);
    }

    @QueryMapping
    public List<Discount> allDiscount() {
        return services.getAllDiscount();
    }

    @MutationMapping
    public Items createItems(@Argument @Valid Items item) {
        return services.addNewItem(item);
    }

    @MutationMapping
    public Items updateItems(@Argument @Valid Items item) {
        return services.updateItem(item);
    }

    @MutationMapping
    public Discount createDiscount(@Argument @Valid Discount discount) {
        return services.addNewDiscount(discount);
    }

    @SchemaMapping(typeName = "Items", field = "discount")
    public Discount getDiscount(Items items) {
        if (items.getDiscountCode() != null)
            return services.getDiscountByCode(items.getDiscountCode());
        else return null;
    }

    @SchemaMapping(typeName = "OrderDetails", field = "discount")
    public Discount getOrderDiscount(OrderDetails orderDetails) {
        if (orderDetails.getDiscountCode() != null)
            return services.getDiscountByCode(orderDetails.getDiscountCode());
        else return null;
    }

    @SchemaMapping(typeName = "OrderDetails", field = "item")
    public Items getOrderItem(OrderDetails orderDetails) {
        return services.getItemByCode(orderDetails.getItemCode());
    }

    @SchemaMapping(typeName = "Discount", field = "discountedItems")
    public List<Items> getDiscountedItems(Discount discount) {
        return services.getItemByDiscountCode(discount.getDiscCode());
    }
}
