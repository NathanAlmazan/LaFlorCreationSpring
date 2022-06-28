package com.nathanael.florcreation.orders.services;

import com.google.gson.Gson;
import com.nathanael.florcreation.orders.dtos.OrderPayment;
import com.nathanael.florcreation.orders.dtos.payconfirm.PaymentConfirm;
import com.nathanael.florcreation.orders.dtos.paysource.PaymentSource;
import com.nathanael.florcreation.orders.mappers.OrdersMapper;
import com.nathanael.florcreation.orders.models.OrdersTable;
import com.nathanael.florcreation.orders.repository.OrderAmountProj;
import com.nathanael.florcreation.orders.repository.OrderPaymentRepository;
import com.nathanael.florcreation.orders.repository.OrdersRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderPaymentServices {
    @Autowired private OrdersServices ordersServices;
    @Autowired private OrdersRepository ordersRepository;
    @Autowired private OrderPaymentRepository orderPaymentRepository;
    @Autowired private OrdersMapper ordersMapper;

    public OrderPayment createPaymentSource(List<String> orderUid, String paymentType) throws IOException {
        double totalAmount = 0;
        for (String uid : orderUid) totalAmount += ordersServices.getOrderAmount(uid);

        double amountInCents = totalAmount * 100;
        int finalAmount = (int) amountInCents;

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(
                "{\"data\":{\"attributes\":{\"amount\":" + finalAmount +
                        ",\"redirect\":{\"success\":\"" + System.getenv("PAYMONGO_SUCCESS_URL") + orderUid.get(0) +
                        "\",\"failed\":\"" + System.getenv("PAYMONGO_FAILED_URL") + orderUid.get(0) +
                        "\"},\"type\":\"" + paymentType.toLowerCase() +
                        "\",\"currency\":\"PHP\"}}}", mediaType);

        Request request = new Request.Builder()
                .url("https://api.paymongo.com/v1/sources")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + System.getenv("PAYMONGO_KEY"))
                .build();

        ResponseBody response = client.newCall(request).execute().body();

        if (response == null) return null;

        Gson gson = new Gson();
        PaymentSource paymentSource = gson.fromJson(response.string(), PaymentSource.class);

        OrderPayment orderPayment = ordersMapper.orderPaymentTableToOrderPayment(
                orderPaymentRepository.createPaymentSource(
                        paymentSource.getData().getId(), paymentSource.getData().getAttributes().getRedirect().getCheckoutUrl())
        );

        for (String uid : orderUid) ordersRepository.addPaymentSource(uid, paymentSource.getData().getId(), paymentType);

        return orderPayment;
    }

    public OrderPayment confirmPaymentSource(String orderUid) throws IOException {
        List<OrdersTable> orders = ordersRepository.findOrdersByPaymentSource(orderUid);
        List<String> ordersUid = new ArrayList<>(orders.size());

;       double totalAmount = 0;
        for (OrdersTable order : orders) {
            totalAmount += ordersServices.getOrderAmount(order.getOrderUid());
            ordersUid.add(order.getOrderUid());
        }
        double amountInCents = totalAmount * 100;
        int finalAmount = (int) amountInCents;

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(
                "{\"data\":{\"attributes\":{\"amount\":" + finalAmount +
                        ",\"source\":{\"id\":\"" + orders.get(0).getPayment().getSourceId() +
                        "\",\"type\":\"source\"},\"currency\":\"PHP\",\"description\":\"Payment for Order " + orderUid +
                        "\"}}}", mediaType);

        Request request = new Request.Builder()
                .url("https://api.paymongo.com/v1/payments")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + System.getenv("PAYMONGO_KEY"))
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() < 300) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) return null;

            Gson gson = new Gson();
            PaymentConfirm paymentConfirm = gson.fromJson(responseBody.string(), PaymentConfirm.class);

            ordersRepository.updateOrdersStatus(ordersUid, "PAID");

            return ordersMapper.orderPaymentTableToOrderPayment(
                    orderPaymentRepository.confirmPayment(orders.get(0).getPayment().getSourceId(), paymentConfirm.getData().getId(), LocalDate.now())
            );
        }

        return null;
    }

    public OrderPayment getOrderPaymentById(String sourceId) {
        return ordersMapper.orderPaymentTableToOrderPayment(
                orderPaymentRepository.findOrderPaymentById(sourceId)
        );
    }
}
