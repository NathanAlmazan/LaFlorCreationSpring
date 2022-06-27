package com.nathanael.florcreation.orders;

import com.nathanael.florcreation.orders.dtos.OrderDetails;
import com.nathanael.florcreation.orders.dtos.Orders;
import com.nathanael.florcreation.orders.dtos.OrdersInput;
import com.nathanael.florcreation.orders.services.OrderDetailsService;
import com.nathanael.florcreation.orders.services.OrdersServices;
import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.dtos.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired private OrdersServices ordersServices;
    @Autowired private OrderDetailsService orderDetailsService;

    @MutationMapping
    public Orders createOrder(
            @Argument("recipient") @Valid Recipient recipient,
            @Argument("order") @Valid OrdersInput order
    ) {
        return ordersServices.createNewOrder(order, recipient);
    }

    @MutationMapping
    public List<OrderDetails> createOrderDetail(@Argument @Valid List<OrderDetails> details) {
        return orderDetailsService.createOrderDetails(details);
    }

    @SchemaMapping(typeName = "Client", field = "clientOrders")
    public List<Orders> getClientOrders(Client client) {
        return ordersServices.getAlClientOrders(client.getClientId());
    }

    @SchemaMapping(typeName = "Orders", field = "orderDetails")
    public List<OrderDetails> getOrderDetails(Orders order) {
        return orderDetailsService.getAllOrderDetails(order.getOrderUid());
    }
}
