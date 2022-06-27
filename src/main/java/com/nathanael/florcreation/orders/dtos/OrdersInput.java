package com.nathanael.florcreation.orders.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
public class OrdersInput {
    @NotNull
    @Length(min = 1, max = 10)
    private String mop;

    @NotNull
    @Length(min = 1, max = 5)
    private String status;

    @NotNull
    private String date;

    @NotNull
    private String time;

    @Length(max = 255)
    private String dNotes;

    @NotNull
    @Length(min = 1, max = 255)
    private String message;

    private Long riderId;

    @Length(max = 255)
    private String fRemarks;

    @NotNull
    @Length(min = 1, max = 30)
    private String clientAccount;
}
