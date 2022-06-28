package com.nathanael.florcreation.orders.repository;

import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import com.nathanael.florcreation.products.models.DiscountTable;

public interface OrderDetailsProj {
    OrderDetailsTable getOrderDetail();
    DiscountTable getOrderDiscount();
    Double getTotalPrice();
}
