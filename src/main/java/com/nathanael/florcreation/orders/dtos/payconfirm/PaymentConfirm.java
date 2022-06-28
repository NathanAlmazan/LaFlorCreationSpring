package com.nathanael.florcreation.orders.dtos.payconfirm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PaymentConfirm {
    @SerializedName("data")
    @Expose
    private Data data;
}
