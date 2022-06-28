package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.models.OrderPaymentTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderPayment {
    private String sourceId;
    private String paymentId;
    private String callbackUrl;
    private String paymentDate;

    public OrderPayment(OrderPaymentTable orderPaymentTable) {
        sourceId = orderPaymentTable.getSourceId();
        paymentId = orderPaymentTable.getPaymentId();
        callbackUrl = orderPaymentTable.getCallbackUrl();

        if (orderPaymentTable.getPaymentDate() != null) {
            paymentDate = orderPaymentTable.getPaymentDate().toString();
        }
    }
}
