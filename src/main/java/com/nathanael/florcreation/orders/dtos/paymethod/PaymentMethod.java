package com.nathanael.florcreation.orders.dtos.paymethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaymentMethod {
    @SerializedName("data")
    @Expose
    public Data data;
}
