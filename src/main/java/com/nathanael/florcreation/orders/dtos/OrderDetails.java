package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import com.nathanael.florcreation.orders.repository.OrderDetailsProj;
import com.nathanael.florcreation.products.dtos.Discount;
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

    private Double finalPrice;

    public OrderDetails(OrderDetailsProj orderDetails) {
        orderUid = orderDetails.getOrderDetail().getId().getOrderUid();
        itemCode =  orderDetails.getOrderDetail().getId().getItemCode();
        quantity =  orderDetails.getOrderDetail().getQuantity();

        if (orderDetails.getOrderDiscount() != null) {
            discountCode = orderDetails.getOrderDiscount().getDiscountCode();
            finalPrice = orderDetails.getTotalPrice() - orderDetails.getOrderDiscount().getDiscountAmount();
        } else {
            finalPrice = orderDetails.getTotalPrice();
        }
    }
}
