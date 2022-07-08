package com.nathanael.florcreation.orders.dtos;

import com.nathanael.florcreation.orders.repository.DailyOrderProj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DailyOrderDto {
    private String deliveryDate;
    private Integer orderCount;

    public DailyOrderDto(DailyOrderProj dailyOrderProj) {
        deliveryDate = dailyOrderProj.getDelivery().toString();
        orderCount = dailyOrderProj.getOrderCount();
    }
}
