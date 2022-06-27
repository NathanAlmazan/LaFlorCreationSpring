package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
    @Length(min = 16, max = 18)
    private String orderUid;

    @NotNull
    @Length(min = 1, max = 5)
    private String itemCode;

    @NotNull
    private Integer quantity;

    @Length(min = 1, max = 5)
    private String discountCode;

    public OrderDetails(OrderDetailsTable orderDetailsTable) {
        orderUid = orderDetailsTable.getId().getOrderUid();
        itemCode = orderDetailsTable.getId().getItemCode();
        quantity = orderDetailsTable.getQuantity();

        if (orderDetailsTable.getDiscount() != null)
            discountCode = orderDetailsTable.getDiscount().getDiscountCode();
    }
}
