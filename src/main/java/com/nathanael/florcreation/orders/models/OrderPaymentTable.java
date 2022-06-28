package com.nathanael.florcreation.orders.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "OrderPaymentTable")
@NoArgsConstructor
@Setter
@Getter
public class OrderPaymentTable {

    @Id
    @Column(name = "source_id", length = 40)
    private String sourceId;

    @Column(name = "payment_id", length = 40)
    private String paymentId;

    @Column(name = "callback_url", length = 100)
    private String callbackUrl;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @OneToMany(mappedBy = "payment")
    private List<OrdersTable> orders;
}
