package com.nathanael.florcreation.orders.models;

import com.nathanael.florcreation.users.models.ClientTable;
import com.nathanael.florcreation.users.models.RecipientTable;
import com.nathanael.florcreation.users.models.RiderTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "OrdersTable")
@NoArgsConstructor
@Getter
@Setter
public class OrdersTable {
    @Id
    @Column(name = "order_uid", updatable = false, length = 18)
    private String orderUid;

    @Column(name = "mop", length = 10, nullable = false)
    private String modeOfPayment;

    @Column(name = "status", length = 5, nullable = false)
    private String status;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "delivery_time", nullable = false)
    private LocalTime deliveryTime;

    @Column(name = "delivery_notes")
    private String deliveryNotes;

    @Column(name = "order_message", nullable = false)
    private String orderMessage;

    @Column(name = "florist_remarks")
    private String floristRemarks;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private ClientTable client;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "recipient_id")
    private RecipientTable recipient;

    @ManyToOne
    @JoinColumn(name = "rider_id", referencedColumnName = "rider_id")
    private RiderTable rider;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderDetailsTable> orderItems;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "source_id")
    private OrderPaymentTable payment;
}
