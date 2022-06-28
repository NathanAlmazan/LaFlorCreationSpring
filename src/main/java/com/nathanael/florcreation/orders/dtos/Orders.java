package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.models.OrdersTable;
import com.nathanael.florcreation.orders.repository.OrderAmountProj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Orders {
    private String orderUid;
    private String mop;
    private String status;
    private String date;
    private String time;
    private String message;
    private String dNotes;
    private String fRemarks;
    private Long clientId;
    private Long recipientId;
    private Long riderId;
    private String paymentId;

    public Orders(OrdersTable ordersTable) {
        orderUid = ordersTable.getOrderUid();
        mop = ordersTable.getModeOfPayment();
        status = ordersTable.getStatus();
        date = ordersTable.getDeliveryDate().toString();
        time = ordersTable.getDeliveryTime().toString();
        message = ordersTable.getOrderMessage();
        dNotes = ordersTable.getDeliveryNotes();
        fRemarks = ordersTable.getFloristRemarks();
        clientId = ordersTable.getClient().getClientId();

        if (ordersTable.getRecipient() != null)
            recipientId = ordersTable.getRecipient().getRecipientId();

        if (ordersTable.getRider() != null)
            riderId = ordersTable.getRider().getRiderId();

        if (ordersTable.getPayment() != null) {
            paymentId = ordersTable.getPayment().getSourceId();
        }
    }
}
