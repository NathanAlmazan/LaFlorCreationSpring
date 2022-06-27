package com.nathanael.florcreation.users.models;

import com.nathanael.florcreation.orders.models.OrdersTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ClientTable")
@NoArgsConstructor
@Getter
@Setter
public class ClientTable {
    @Id
    @SequenceGenerator(name = "client_client_id_seq", sequenceName = "client_client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_client_id_seq")
    @Column(name = "client_id", updatable = false)
    private Long clientId;

    @Column(name = "account_uid", length = 30, unique = true)
    private String accountUid;

    @Column(name = "client_name", nullable = false, length = 50)
    private String clientName;

    @Column(name = "client_contact", nullable = false, length = 12)
    private String clientContact;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<OrdersTable> clientOrders;
}
