package com.nathanael.florcreation.products.dtos;

import com.nathanael.florcreation.products.models.DiscountTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class Discount {
    @NotNull
    @Length(min = 1, max = 5)
    private String discCode;

    @NotNull
    private Double discAmount;

    public Discount(DiscountTable discountTable) {
        discCode = discountTable.getDiscountCode();
        discAmount = discountTable.getDiscountAmount();
    }
}
