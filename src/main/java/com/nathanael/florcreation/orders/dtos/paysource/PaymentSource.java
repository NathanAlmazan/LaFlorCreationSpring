package com.nathanael.florcreation.orders.dtos.paysource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaymentSource {
    @SerializedName("data")
    @Expose
    private Data data;
}
