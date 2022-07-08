package com.nathanael.florcreation.orders.mappers;

import com.nathanael.florcreation.orders.dtos.*;
import com.nathanael.florcreation.orders.models.OrderDetailsTable;
import com.nathanael.florcreation.orders.models.OrderPaymentTable;
import com.nathanael.florcreation.orders.models.OrdersTable;
import com.nathanael.florcreation.orders.repository.DailyOrderProj;
import com.nathanael.florcreation.orders.repository.OrderDetailsProj;
import com.nathanael.florcreation.orders.repository.ProvinceRankingProj;
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

    public OrderDetails orderDetailsTableToOrderDetails(OrderDetailsProj orderDetails) {
        return new OrderDetails(orderDetails);
    }

    public List<OrderDetails> orderDetailsTableToOrderDetailsList(List<OrderDetailsProj> orderDetailsTables) {
        List<OrderDetails> orders = new ArrayList<>(orderDetailsTables.size());

        for (OrderDetailsProj order : orderDetailsTables)
            orders.add(orderDetailsTableToOrderDetails(order));

        return orders;
    }

    public OrderPayment orderPaymentTableToOrderPayment(OrderPaymentTable orderPaymentTable) {
        return new OrderPayment(orderPaymentTable);
    }

    public ProvinceRankDto provinceRankProjToDto(ProvinceRankingProj provinceRankingProj) {
        return new ProvinceRankDto(provinceRankingProj);
    }

    public List<ProvinceRankDto> provinceRankProjListToDtoList(List<ProvinceRankingProj> provinceRankingProj) {
        List<ProvinceRankDto> provinces = new ArrayList<>(provinceRankingProj.size());

        for (ProvinceRankingProj province : provinceRankingProj)
            provinces.add(provinceRankProjToDto(province));

        return provinces;
    }

    public DailyOrderDto dailyOrderProjToDto(DailyOrderProj dailyOrderProj) {
        return new DailyOrderDto(dailyOrderProj);
    }

    public List<DailyOrderDto> dailyOrderProjListToDtoList(List<DailyOrderProj> dailyOrderProj) {
        List<DailyOrderDto> reports = new ArrayList<>(dailyOrderProj.size());

        for (DailyOrderProj report : dailyOrderProj)
            reports.add(dailyOrderProjToDto(report));

        return reports;
    }
}
