package com.nathanael.florcreation.users.models;

import com.nathanael.florcreation.orders.models.OrdersTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RiderTable")
@NoArgsConstructor
@Getter
@Setter
public class RiderTable {
    @Id
    @SequenceGenerator(name = "rider_rider_id_seq", sequenceName = "rider_rider_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rider_rider_id_seq")
    @Column(name = "rider_id", updatable = false)
    private Long riderId;

    @Column(name = "account_uid", length = 30, unique = true)
    private String accountUid;

    @Column(name = "rider_name", nullable = false, length = 50)
    private String riderName;

    @Column(name = "rider_contact", length = 12)
    private String riderContact;

    @Column(name = "rider_image", length = 200)
    private String riderImage;

    @Column(name = "rider_city", nullable = false, length = 15)
    private String riderCity;

    @Column(name = "rider_province", nullable = false, length = 15)
    private String riderProvince;

    @OneToMany(mappedBy = "rider", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrdersTable> ordersToDeliver;
}
