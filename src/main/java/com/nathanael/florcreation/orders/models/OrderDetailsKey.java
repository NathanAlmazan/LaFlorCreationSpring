package com.nathanael.florcreation.orders.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class OrderDetailsKey implements Serializable {
    @Column(name = "item_code", length = 5, nullable = false)
    private String itemCode;

    @Column(name = "order_uid", length = 18, nullable = false)
    private String orderUid;
}
