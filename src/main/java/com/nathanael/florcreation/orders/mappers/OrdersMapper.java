package com.nathanael.florcreation.orders.mappers;

import com.nathanael.florcreation.orders.dtos.OrderDetails;
import com.nathanael.florcreation.orders.dtos.Orders;
import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import com.nathanael.florcreation.orders.models.OrdersTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersMapper {

    public Orders orderTableToOrders(OrdersTable ordersTable) {
        return new Orders(ordersTable);
    }

    public List<Orders> orderTableToOrdersList(List<OrdersTable> ordersTables) {
        List<Orders> orders = new ArrayList<>(ordersTables.size());

        for (OrdersTable order : ordersTables)
            orders.add(orderTableToOrders(order));

        return orders;
    }

    public OrderDetails orderDetailsTableToOrderDetails(OrderDetailsTable orderDetailsTable) {
        return new OrderDetails(orderDetailsTable);
    }

    public List<OrderDetails> orderDetailsTableToOrderDetailsList(List<OrderDetailsTable> orderDetailsTables) {
        List<OrderDetails> orders = new ArrayList<>(orderDetailsTables.size());

        for (OrderDetailsTable order : orderDetailsTables)
            orders.add(orderDetailsTableToOrderDetails(order));

        return orders;
    }
}
