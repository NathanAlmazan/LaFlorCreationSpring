package com.nathanael.florcreation.orders.repository;

import java.time.LocalDate;

public interface DailyOrderProj {
    LocalDate getDelivery();
    Integer getOrderCount();
}
