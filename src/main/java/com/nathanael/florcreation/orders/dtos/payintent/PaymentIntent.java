package com.nathanael.florcreation.orders.dtos.payintent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@NoArgsConstructor
@Getter
@Setter
public class PaymentIntent {
    @SerializedName("data")
    @Expose
    public Data data;
}
