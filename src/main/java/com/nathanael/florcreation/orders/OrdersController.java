package com.nathanael.florcreation.orders;

import com.nathanael.florcreation.errors.InvalidArgumentException;
import com.nathanael.florcreation.orders.dtos.OrderDetails;
import com.nathanael.florcreation.orders.dtos.OrderPayment;
import com.nathanael.florcreation.orders.dtos.Orders;
import com.nathanael.florcreation.orders.dtos.OrdersInput;
import com.nathanael.florcreation.orders.services.OrderDetailsService;
import com.nathanael.florcreation.orders.services.OrderPaymentServices;
import com.nathanael.florcreation.orders.services.OrdersServices;
import com.nathanael.florcreation.users.dtos.Client;
import com.nathanael.florcreation.users.dtos.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired private OrdersServices ordersServices;
    @Autowired private OrderDetailsService orderDetailsService;
    @Autowired private OrderPaymentServices orderPaymentServices;

    @QueryMapping
    public Orders orderById(@Argument String orderUid) {
        return ordersServices.getOrderByUid(orderUid);
    }

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

    @MutationMapping
    public OrderPayment createPayment(@Argument("orderUid") List<String> uid, @Argument("paymentType") String type) {
        try {
            return orderPaymentServices.createPaymentSource(uid, type);
        } catch (IOException e) {
            throw new InvalidArgumentException("Failed to parse request.");
        }
    }

    @MutationMapping
    public OrderPayment confirmPayment(@Argument("orderUid") String uid) {
        try {
            return orderPaymentServices.confirmPaymentSource(uid);
        } catch (IOException e) {
            throw new InvalidArgumentException("Failed to parse request.");
        }
    }

    @SchemaMapping(typeName = "Client", field = "clientOrders")
    public List<Orders> getClientOrders(Client client) {
        return ordersServices.getAlClientOrders(client.getClientId());
    }

    @SchemaMapping(typeName = "Orders", field = "orderDetails")
    public List<OrderDetails> getOrderDetails(Orders order) {
        return orderDetailsService.getAllOrderDetails(order.getOrderUid());
    }

    @SchemaMapping(typeName = "Orders", field = "amount")
    public Double getAmount(Orders orders) {
        return ordersServices.getOrderAmount(orders.getOrderUid());
    }

    @SchemaMapping(typeName = "Orders", field = "payment")
    public OrderPayment getOrdersPayment(Orders orders) {
        if (orders.getPaymentId() != null) return orderPaymentServices.getOrderPaymentById(orders.getPaymentId());
        return null;
    }
}
