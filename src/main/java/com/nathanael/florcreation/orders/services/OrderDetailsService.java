package com.nathanael.florcreation.orders.services;

import com.nathanael.florcreation.orders.dtos.OrderDetails;
import com.nathanael.florcreation.orders.mappers.OrdersMapper;
import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import com.nathanael.florcreation.orders.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired private OrderDetailsRepository orderDetailsRepository;
    @Autowired private OrdersMapper ordersMapper;

    public List<OrderDetails> createOrderDetails(List<OrderDetails> orderDetails) {
        List<OrderDetailsTable> orders = new ArrayList<>(orderDetails.size());

        for (OrderDetails order : orderDetails)
           orders.add( orderDetailsRepository.createOrderDetail(
                   order.getItemCode(),
                   order.getOrderUid(),
                   order.getQuantity(),
                   order.getDiscountCode()
           ));

        return ordersMapper.orderDetailsTableToOrderDetailsList(orders);
    }

    public List<OrderDetails> getAllOrderDetails(String orderUid) {
        return ordersMapper.orderDetailsTableToOrderDetailsList(
                orderDetailsRepository.getByOrderUid(orderUid)
        );
    }
}
