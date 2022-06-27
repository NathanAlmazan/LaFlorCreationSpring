package com.nathanael.florcreation.orders.services;

import com.nathanael.florcreation.errors.InvalidArgumentException;
import com.nathanael.florcreation.orders.dtos.Orders;
import com.nathanael.florcreation.orders.dtos.OrdersInput;
import com.nathanael.florcreation.orders.mappers.OrdersMapper;
import com.nathanael.florcreation.orders.repository.OrdersRepository;
import com.nathanael.florcreation.users.dtos.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrdersServices {
    @Autowired private OrdersRepository ordersRepository;
    @Autowired private OrdersMapper ordersMapper;

    public List<Orders> getAlClientOrders(Long clientId) {
        return ordersMapper.orderTableToOrdersList(
                ordersRepository.findOrdersByClient(clientId)
        );
    }

    public Orders createNewOrder(OrdersInput newOrder, Recipient recipient) {
        try {
            return ordersMapper.orderTableToOrders(
                    ordersRepository.createOrder(
                            newOrder.getClientAccount(),
                            recipient.getRecipientName(),
                            recipient.getRecipientContact(),
                            recipient.getRecipientStreet(),
                            recipient.getRecipientCity(),
                            recipient.getRecipientProvince(),
                            recipient.getLatitude(),
                            recipient.getLongitude(),
                            LocalDate.parse(newOrder.getDate()),
                            LocalTime.parse(newOrder.getTime()),
                            newOrder.getMop(),
                            newOrder.getMessage(),
                            newOrder.getStatus(),
                            newOrder.getRiderId(),
                            newOrder.getDNotes(),
                            newOrder.getFRemarks()
                    )
            );
        } catch (Exception ex) {
            throw new InvalidArgumentException(ex.getMessage());
        }
    }
}
