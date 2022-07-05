package com.nathanael.florcreation.orders.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class CardPayment {
    @NotNull
    @Length(min = 10, max = 20)
    private String cardNumber;

    @NotNull
    private Integer expMonth;

    @NotNull
    private Integer expYear;

    @NotNull
    private Integer cvcNumber;

    @NotNull
    private String paymentType;
}
