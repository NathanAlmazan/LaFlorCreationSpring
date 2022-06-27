package com.nathanael.florcreation.users.models;

import com.nathanael.florcreation.orders.models.OrdersTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RecipientTable")
@NoArgsConstructor
@Getter
@Setter
public class RecipientTable {
    @Id
    @SequenceGenerator(name = "recipient_recipient_id_seq", sequenceName = "recipient_recipient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipient_recipient_id_seq")
    @Column(name = "recipient_id", updatable = false)
    private Long recipientId;

    @Column(name = "recipient_name", nullable = false, length = 50)
    private String recipientName;

    @Column(name = "recipient_contact", nullable = false, length = 12)
    private String recipientContact;

    @Column(name = "recipient_street", nullable = false, length = 50)
    private String recipientStreet;

    @Column(name = "recipient_city", nullable = false, length = 50)
    private String recipientCity;

    @Column(name = "recipient_province", nullable = false, length = 50)
    private String recipientProvince;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private List<OrdersTable> receivedOrders;
}
